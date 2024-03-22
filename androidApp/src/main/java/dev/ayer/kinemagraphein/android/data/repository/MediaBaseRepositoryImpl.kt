package dev.ayer.kinemagraphein.android.data.repository

import dev.ayer.kinemagraphein.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.android.data.adapter.toFavoritesTable
import dev.ayer.kinemagraphein.android.data.adapter.toMediaBaseData
import dev.ayer.kinemagraphein.data.adapter.toMediaBaseData
import dev.ayer.kinemagraphein.android.data.adapter.toRecentTable
import dev.ayer.kinemagraphein.data.dto.ShowModelBase
import dev.ayer.kinemagraphein.data.api.KtorfitApiService
import dev.ayer.kinemagraphein.android.data.sources.room.AppRoomDatabase
import dev.ayer.kinemagraphein.android.data.sources.room.entity.FavoritesTable
import dev.ayer.kinemagraphein.android.data.sources.room.entity.RecentTable
import dev.ayer.kinemagraphein.entity.media.MediaBaseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Date

class MediaBaseRepositoryImpl: MediaBaseRepository, KoinComponent {

    private val ktorfitApiService: KtorfitApiService by inject()
    private val database: AppRoomDatabase by inject()

    private val showListState = MutableStateFlow<List<MediaBaseData>>(emptyList())
    private var currentPage = 1

    override suspend fun searchMedia(query: String): Flow<List<MediaBaseData>> {
        val apiResult = ktorfitApiService.search(query) ?: emptyList()
        val result = apiResult.map {
            val tvShow = it.show
            tvShow.toMediaBaseData(isFavorite = tvShow.isFavorite())
        }

        return flow { emit(result) }
    }

    override suspend fun addAsFavorite(media: MediaBaseData) {
        database.favoriteDao().insert(media.toFavoritesTable())
    }

    override suspend fun removeFromFavorite(media: MediaBaseData) {
        database.favoriteDao().delete(media.toFavoritesTable())
    }

    override suspend fun registerAccess(media: MediaBaseData) {
        val lastAccess = Date().time
        val recentRegister = media.toRecentTable(lastAccess)
        database.recentDao().insertWithReplace(recentRegister)
    }


    override suspend fun loadRecentMedia(): Flow<List<MediaBaseData>> {
        return database.recentDao().getAll().map { list ->
            list.map { it.toMediaBaseData(it.isFavorite()) }
        }
    }

    override suspend fun loadFavoriteMedia(): Flow<List<MediaBaseData>> {
        return database.favoriteDao().getAll().map { list ->
            list.map { it.toMediaBaseData(it.isFavorite()) }
        }
    }

    override suspend fun loadMoreShowItems(): StateFlow<List<MediaBaseData>> {
        val apiResult = ktorfitApiService.getShows(page = currentPage) ?: emptyList()
        val result = apiResult.map {
            it.toMediaBaseData(isFavorite = it.isFavorite())
        }

        if (result.isNotEmpty()) {
            currentPage++
        }

        val newResult = showListState.value + result
        showListState.emit(newResult)
        return showListState
    }

    private suspend fun ShowModelBase.isFavorite(): Boolean {
        return database.favoriteDao().existInFavorites(this.id.toString())
    }

    private suspend fun RecentTable.isFavorite(): Boolean {
        return database.favoriteDao().existInFavorites(this.id)
    }

    private suspend fun FavoritesTable.isFavorite(): Boolean {
        return database.favoriteDao().existInFavorites(this.id)
    }

    private suspend fun MediaBaseData.isFavorite(): Boolean {
        return database.favoriteDao().existInFavorites(this.id)
    }
}

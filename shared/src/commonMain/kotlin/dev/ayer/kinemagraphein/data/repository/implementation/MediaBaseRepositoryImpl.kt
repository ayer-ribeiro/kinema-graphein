package dev.ayer.kinemagraphein.data.repository.implementation

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import dev.ayer.kinemagraphein.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.data.api.KtorfitApiService
import dev.ayer.kinemagraphein.data.api.adapter.toMediaBaseData
import dev.ayer.kinemagraphein.data.api.adapter.toShow
import dev.ayer.kinemagraphein.data.database.KinemaDatabase
import dev.ayer.kinemagraphein.data.dto.ShowModelBase
import dev.ayer.kinemagraphein.data.dto.ShowModelComplete
import dev.ayer.kinemagraphein.entity.media.Show
import dev.ayer.kinemagraphein.entity.media.ShowBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.coroutineContext

class MediaBaseRepositoryImpl : MediaBaseRepository, KoinComponent {

    private val ktorfitApiService: KtorfitApiService by inject()
    private val database: KinemaDatabase by inject()

    private val showListState = MutableStateFlow<List<ShowBase>>(emptyList())
    private var currentPage = 0
    // change to https://medium.com/@asia_sama/paging-3-with-kmp-kotlin-multiplatform-811541c0f297

    override suspend fun searchMedia(query: String): Flow<List<ShowBase>> {
        val apiResult = ktorfitApiService.search(query) ?: emptyList()
        val result = apiResult.map { it.show.toShowBase() }
        return flow { emit(result) }
    }

    private val showStateFlow = MutableStateFlow<Show?>(null)
    override suspend fun fetchShow(id: String): Flow<Show?> {
        val result = ktorfitApiService.getShow(id)?.toShow()
        showStateFlow.emit(result)
        return showStateFlow
    }

    private suspend fun ShowModelComplete.toShow(): Show {
        val showId = id.toString()

        val databaseEntry = database
            .showsQueries
            .getShow(showId)
            .asFlow()
            .mapToOneOrNull(coroutineContext)
            .firstOrNull()

        return this.toShow(
            isFavorite = databaseEntry?.is_favorite ?: false,
            lastAccessed = databaseEntry?.last_access
        )
    }

    private suspend fun ShowModelBase.toShowBase(): ShowBase {
        val showId = id.toString()

        val databaseEntry = database
            .showsQueries
            .getShow(showId)
            .asFlow()
            .mapToOneOrNull(coroutineContext)
            .firstOrNull()

        return this.toMediaBaseData(
            isFavorite = databaseEntry?.is_favorite ?: false,
            lastAccess = databaseEntry?.last_access
        )
    }

    override suspend fun addAsFavorite(media: ShowBase) {
        database.showsQueries.insertFavorite(
            id = media.id,
            name = media.name,
            summary = media.summary,
            genres = media.genres,
            schedule_time = media.schedule.time,
            schedule_days = media.schedule.weekdays,
            medium_image_url = media.mediumImageUrl,
            original_image_url = media.originalImageUrl,
            release_date = media.releaseDate,
            last_access = media.lastAccess,
        )

        showListState.lastOrNull()?.let { showList ->
            val updatedList = showList.withUpdated(media)
            showListState.emit(updatedList)
        }
    }

    private fun List<ShowBase>.withUpdated(element: ShowBase): List<ShowBase> {
        val indexOfElementInList = this.indexOfFirst { it.id == element.id }
        if (indexOfElementInList < 0) {
            return this
        }
        val newList = ArrayList<ShowBase>(this)
        newList.remove(element)
        newList.add(indexOfElementInList, element)
        return newList
    }

    override suspend fun removeFromFavorite(media: ShowBase) {
        database.showsQueries.removeFavorite(media.id)
    }

    override suspend fun registerAccess(media: Show) {
        val now = Clock.System.now()
        database.showsQueries
        database.showsQueries.registerAccess(
            time = now,
            id = media.id
        )
    }

    override suspend fun loadRecentMedia(): Flow<List<ShowBase>> {
        return database
            .showsQueries
            .getRecents()
            .asFlow()
            .mapToList(coroutineContext).map { it.toMediaBaseData() }
    }

    override suspend fun loadFavoriteMedia(): Flow<List<ShowBase>> {
        return database
            .showsQueries
            .getFavorites()
            .asFlow()
            .mapToList(coroutineContext)
            .map { it.toMediaBaseData() }
    }

    override suspend fun loadMoreShowItems(): StateFlow<List<ShowBase>> {
        val apiResult = ktorfitApiService.getShows(page = currentPage) ?: emptyList()
        val result = apiResult.map { it.toShowBase() }

        if (result.isNotEmpty()) {
            currentPage++
        }

        val newResult = showListState.value + result
        showListState.emit(newResult)
        return showListState
    }
}

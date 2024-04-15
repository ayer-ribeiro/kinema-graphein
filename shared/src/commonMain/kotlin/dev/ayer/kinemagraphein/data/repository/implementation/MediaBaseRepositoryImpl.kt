package dev.ayer.kinemagraphein.data.repository.implementation

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import app.cash.sqldelight.coroutines.mapToOneOrNull
import dev.ayer.kinemagraphein.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.core.repository.QueryLimit
import dev.ayer.kinemagraphein.data.api.KtorfitApiService
import dev.ayer.kinemagraphein.data.api.adapter.toMediaBaseData
import dev.ayer.kinemagraphein.data.api.adapter.toShow
import dev.ayer.kinemagraphein.data.database.KinemaDatabase
import dev.ayer.kinemagraphein.data.dto.ShowModelBase
import dev.ayer.kinemagraphein.data.dto.ShowModelComplete
import dev.ayer.kinemagraphein.entity.UserFavorite
import dev.ayer.kinemagraphein.entity.media.Show
import dev.ayer.kinemagraphein.entity.media.ShowBase
import dev.ayer.kinemagraphein.entity.media.asShowBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.coroutineContext
import kotlin.math.floor

class MediaBaseRepositoryImpl : MediaBaseRepository, KoinComponent {

    private val ktorfitApiService: KtorfitApiService by inject()
    private val database: KinemaDatabase by inject()

    private val showListState = MutableStateFlow<List<ShowBase>>(emptyList())

    private var databasePage = 0L
    // change to https://medium.com/@asia_sama/paging-3-with-kmp-kotlin-multiplatform-811541c0f297

    override suspend fun searchMedia(query: String): Flow<List<ShowBase>> {
        val apiResult = ktorfitApiService.search(query) ?: emptyList()
        val result = apiResult.map { it.show.toShowBase() }
        return flow { emit(result) }
    }

    override suspend fun fetchShow(id: Long): Flow<Show?> {
        val result = ktorfitApiService.getShow(id)

        if (result != null) {
            insertOnDatabase(result.toShow().asShowBase())
        }

        return database
            .showsQueries
            .getShow(id)
            .asFlow()
            .mapToOneOrNull(coroutineContext)
            .map {
                result?.toShow(
                    isFavorite = it?.is_favorite == true,
                    lastAccessed = it?.last_access,
                    lastModified = it?.last_modified
                )
            }
    }

    private suspend fun ShowModelComplete.toShow(): Show {
        val databaseEntry = database
            .showsQueries
            .getShow(id)
            .asFlow()
            .mapToOneOrNull(coroutineContext)
            .firstOrNull()

        return this.toShow(
            isFavorite = databaseEntry?.is_favorite ?: false,
            lastAccessed = databaseEntry?.last_access,
            lastModified = databaseEntry?.last_modified
        )
    }

    private suspend fun ShowModelBase.toShowBase(): ShowBase {
        val databaseEntry = database
            .showsQueries
            .getShow(id)
            .asFlow()
            .mapToOneOrNull(coroutineContext)
            .firstOrNull()

        return this.toMediaBaseData(
            isFavorite = databaseEntry?.is_favorite ?: false,
            lastAccess = databaseEntry?.last_access,
            lastModified = databaseEntry?.last_modified
        )
    }

    override suspend fun addAsFavorite(media: ShowBase) {
        insertOnDatabase(media)
        database.transaction {
            database.showsQueries.updateFavorite(media.id)
            database.showsQueries.updateLastModified(
                id = media.id,
                time = Clock.System.now()
            )
        }

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
        database.showsQueries.updateFavorite(media.id)
    }

    override suspend fun updateFavoriteState(mediaId: Long) {
        database.showsQueries.updateFavorite(mediaId)
    }

    override suspend fun registerAccess(media: Show) {
        val now = Clock.System.now()
        insertOnDatabase(media)
        database.showsQueries.registerAccess(
            time = now,
            id = media.id
        )
    }

    private fun insertOnDatabase(media: Show) {
        insertOnDatabase(media.asShowBase())
    }

    private fun insertOnDatabase(media: ShowBase) {
        database.showsQueries.insert(
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
            is_favorite = media.isFavorite,
            last_modified = media.lastModified,
        )
    }

    override suspend fun loadRecentMedia(): Flow<List<ShowBase>> {
        return database
            .showsQueries
            .getRecents()
            .asFlow()
            .mapToList(coroutineContext).map { it.toMediaBaseData() }
    }

    override suspend fun loadFavoriteMedia(limit: QueryLimit): Flow<UserFavorite> {
        val showQueries = database.showsQueries

        val query = if (limit.hasLimit()) {
            showQueries.getFavoritesLimitedTo(limit = limit.value)
        } else {
            showQueries.getFavorites()
        }

        val favoriteCount = database
            .showsQueries
            .getFavoritesCount()
            .asFlow()
            .mapToOneOrNull(coroutineContext)
            .first() ?: 0L

        return query
            .asFlow()
            .mapToList(coroutineContext)
            .map {
                UserFavorite(
                    items = it.toMediaBaseData(),
                    containsAllFavorites = it.size < favoriteCount
                )
            }
    }

    override suspend fun loadMoreShowItems(): Flow<List<ShowBase>> {
        val resultsPerPage = 10L

        val lastIdSync = database
            .showsQueries
            .getLastIdSync()
            .asFlow()
            .mapToOne(coroutineContext)
            .first()
            .lastId ?: 0

        if (lastIdSync < resultsPerPage * (databasePage + 1)) {
            val apiPage = getApiPageToSync(lastIdSync)

            val apiResult = ktorfitApiService.getShows(page = apiPage) ?: emptyList()
            val result = apiResult.map { it.toShowBase() }

            database.transaction {
                result.forEach {
                    insertOnDatabase(it)
                }
            }
        }

        return database
            .showsQueries
            .getShows(resultsPerPage = resultsPerPage * ++databasePage)
            .asFlow()
            .mapToList(coroutineContext)
            .map { it.toMediaBaseData() }
    }

    private fun getApiPageToSync(lastIdSync: Long): Long {
        return floor((lastIdSync + 1).toDouble() / 250.0).toLong()
    }
}
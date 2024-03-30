package dev.ayer.kinemagraphein.core.repository

import dev.ayer.kinemagraphein.entity.media.Show
import dev.ayer.kinemagraphein.entity.media.ShowBase
import dev.ayer.kinemagraphein.entity.media.ShowBaseData
import kotlinx.coroutines.flow.Flow

interface MediaBaseRepository {
    suspend fun addAsFavorite(media: ShowBaseData)
    suspend fun removeFromFavorite(media: ShowBaseData)
    suspend fun registerAccess(media: ShowBaseData)

    suspend fun loadRecentMedia(): Flow<List<ShowBaseData>>
    suspend fun loadFavoriteMedia(): Flow<List<ShowBaseData>>
    suspend fun searchMedia(query: String): Flow<List<ShowBase>>
    suspend fun loadMoreShowItems(): Flow<List<ShowBaseData>>

    suspend fun fetchShow(id: String): Flow<Show?>
}

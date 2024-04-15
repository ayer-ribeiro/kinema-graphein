package dev.ayer.kinemagraphein.core.repository

import dev.ayer.kinemagraphein.entity.UserFavorite
import dev.ayer.kinemagraphein.entity.media.Show
import dev.ayer.kinemagraphein.entity.media.ShowBase
import kotlinx.coroutines.flow.Flow

interface MediaBaseRepository {
    suspend fun addAsFavorite(media: ShowBase)
    suspend fun removeFromFavorite(media: ShowBase)
    suspend fun updateFavoriteState(mediaId: Long)
    suspend fun registerAccess(media: Show)

    suspend fun loadRecentMedia(): Flow<List<ShowBase>>
    suspend fun loadFavoriteMedia(limit: QueryLimit = QueryLimit.NoLimit): Flow<UserFavorite>
    suspend fun searchMedia(query: String): Flow<List<ShowBase>>
    suspend fun loadMoreShowItems(): Flow<List<ShowBase>>

    suspend fun fetchShow(id: Long): Flow<Show?>
}

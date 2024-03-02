package dev.ayer.kinemagraphein.android.core.repository

import dev.ayer.kinemagraphein.android.entity.media.MediaBaseData
import kotlinx.coroutines.flow.Flow

interface MediaBaseRepository {
    suspend fun addAsFavorite(media: MediaBaseData)
    suspend fun removeFromFavorite(media: MediaBaseData)
    suspend fun registerAccess(media: MediaBaseData)

    suspend fun loadRecentMedia(): Flow<List<MediaBaseData>>
    suspend fun loadFavoriteMedia(): Flow<List<MediaBaseData>>
    suspend fun searchMedia(query: String): Flow<List<MediaBaseData>>
    suspend fun loadMoreShowItems(): Flow<List<MediaBaseData>>
}

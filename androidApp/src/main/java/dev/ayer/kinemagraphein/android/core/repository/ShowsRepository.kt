package dev.ayer.kinemagraphein.android.core.repository

import dev.ayer.kinemagraphein.android.entity.media.Show
import kotlinx.coroutines.flow.Flow

interface ShowsRepository {
    suspend fun fetchShow(id: String): Flow<Show?>
}

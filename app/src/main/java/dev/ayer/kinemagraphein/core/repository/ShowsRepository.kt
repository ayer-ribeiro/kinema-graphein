package dev.ayer.kinemagraphein.core.repository

import dev.ayer.kinemagraphein.entity.media.Show
import kotlinx.coroutines.flow.Flow

interface ShowsRepository {
    suspend fun fetchShow(id: String): Flow<Show?>
}
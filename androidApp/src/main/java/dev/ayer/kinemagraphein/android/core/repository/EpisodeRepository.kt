package dev.ayer.kinemagraphein.android.core.repository

import dev.ayer.kinemagraphein.android.entity.media.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    suspend fun fetch(showId: String, season: Int, number: Int): Flow<Episode?>
}

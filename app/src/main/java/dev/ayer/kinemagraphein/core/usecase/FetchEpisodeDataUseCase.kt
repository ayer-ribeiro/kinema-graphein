package dev.ayer.kinemagraphein.core.usecase

import dev.ayer.kinemagraphein.entity.media.Episode
import kotlinx.coroutines.flow.Flow

interface FetchEpisodeDataUseCase {
    suspend operator fun invoke(seriesId: String, season: Int, number: Int): Flow<Episode?>
}
package dev.ayer.kinemagraphein.domain.usecase

import dev.ayer.kinemagraphein.core.repository.EpisodeRepository
import dev.ayer.kinemagraphein.core.usecase.FetchEpisodeDataUseCase
import dev.ayer.kinemagraphein.entity.media.Episode
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class FetchEpisodeDataUseCaseImpl: FetchEpisodeDataUseCase, KoinComponent {

    private val episodeRepository: EpisodeRepository by inject()

    override suspend fun invoke(seriesId: String, season: Int, number: Int): Flow<Episode?> {
        return episodeRepository.fetch(showId = seriesId, season = season, number = number)
    }
}

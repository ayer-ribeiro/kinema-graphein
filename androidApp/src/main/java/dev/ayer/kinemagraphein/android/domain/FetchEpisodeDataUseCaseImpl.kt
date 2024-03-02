package dev.ayer.kinemagraphein.android.domain

import dev.ayer.kinemagraphein.android.core.repository.EpisodeRepository
import dev.ayer.kinemagraphein.android.core.usecase.FetchEpisodeDataUseCase
import dev.ayer.kinemagraphein.android.entity.media.Episode
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FetchEpisodeDataUseCaseImpl: FetchEpisodeDataUseCase, KoinComponent {

    private val episodeRepository: EpisodeRepository by inject()

    override suspend fun invoke(seriesId: String, season: Int, number: Int): Flow<Episode?> {
        return episodeRepository.fetch(showId = seriesId, season = season, number = number)
    }
}

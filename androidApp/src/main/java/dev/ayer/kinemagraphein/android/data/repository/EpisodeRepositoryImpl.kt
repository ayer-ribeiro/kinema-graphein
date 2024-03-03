package dev.ayer.kinemagraphein.android.data.repository

import dev.ayer.kinemagraphein.core.repository.EpisodeRepository
import dev.ayer.kinemagraphein.android.data.adapter.toEpisode
import dev.ayer.kinemagraphein.android.data.sources.RetrofitApiService
import dev.ayer.kinemagraphein.entity.media.Episode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EpisodeRepositoryImpl: EpisodeRepository, KoinComponent {

    private val retrofitApiService: RetrofitApiService by inject()

    private val episodeStateFlow = MutableStateFlow<Episode?>(null)

    override suspend fun fetch(showId: String, season: Int, number: Int): Flow<Episode?> {
        val result = retrofitApiService.getEpisode(
            showId = showId,
            season = season,
            number = number
        ).toEpisode()

        episodeStateFlow.emit(result)
        return episodeStateFlow
    }
}

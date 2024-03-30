package dev.ayer.kinemagraphein.domain.usecase

import dev.ayer.kinemagraphein.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.core.usecase.FetchSeriesDataUseCase
import dev.ayer.kinemagraphein.entity.media.Show
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class FetchSeriesDataUseCaseImpl: FetchSeriesDataUseCase, KoinComponent {

    private val mediaBaseRepository: MediaBaseRepository by inject()

    override suspend fun invoke(id: String): Flow<Show?> {
        val tvShow = mediaBaseRepository.fetchShow(id)
        val tvShowItem = mediaBaseRepository.fetchShow(id).firstOrNull()

        if (tvShowItem != null) {
            mediaBaseRepository.registerAccess(tvShowItem)
        }

        return tvShow
    }
}

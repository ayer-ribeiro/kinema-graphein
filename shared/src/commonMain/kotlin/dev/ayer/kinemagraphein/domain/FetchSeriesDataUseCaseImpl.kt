package dev.ayer.kinemagraphein.domain

import dev.ayer.kinemagraphein.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.core.repository.ShowsRepository
import dev.ayer.kinemagraphein.core.usecase.FetchSeriesDataUseCase
import dev.ayer.kinemagraphein.entity.media.Show
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FetchSeriesDataUseCaseImpl: FetchSeriesDataUseCase, KoinComponent {

    private val showsRepository: ShowsRepository by inject()
    private val mediaBaseRepository: MediaBaseRepository by inject()

    override suspend fun invoke(id: String): Flow<Show?> {
        val tvShow = showsRepository.fetchShow(id)
        val tvShowItem = showsRepository.fetchShow(id).firstOrNull()

        if (tvShowItem != null) {
            mediaBaseRepository.registerAccess(tvShowItem)
        }

        return tvShow
    }
}

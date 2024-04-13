package dev.ayer.kinemagraphein.domain.usecase

import dev.ayer.kinemagraphein.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.core.usecase.FetchShowDataUseCase
import dev.ayer.kinemagraphein.entity.media.Show
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class FetchShowDataUseCaseImpl: FetchShowDataUseCase, KoinComponent {

    private val mediaBaseRepository: MediaBaseRepository by inject()

    override suspend fun invoke(id: Long): Flow<Show?> {
        return mediaBaseRepository.fetchShow(id)
    }
}

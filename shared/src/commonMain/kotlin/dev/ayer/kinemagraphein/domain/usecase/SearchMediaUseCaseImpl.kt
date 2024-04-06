package dev.ayer.kinemagraphein.domain.usecase

import dev.ayer.kinemagraphein.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.core.usecase.SearchMediaUseCase
import dev.ayer.kinemagraphein.entity.media.ShowBase
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class SearchMediaUseCaseImpl: SearchMediaUseCase, KoinComponent {

    private val mediaBaseRepository: MediaBaseRepository by inject()

    override suspend fun invoke(query: String): Flow<List<ShowBase>> {
        return mediaBaseRepository.searchMedia(query)
    }
}

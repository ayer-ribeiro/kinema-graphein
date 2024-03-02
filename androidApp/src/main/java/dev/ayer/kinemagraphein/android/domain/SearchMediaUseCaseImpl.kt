package dev.ayer.kinemagraphein.android.domain

import dev.ayer.kinemagraphein.android.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.android.core.usecase.SearchMediaUseCase
import dev.ayer.kinemagraphein.entity.media.MediaBaseData
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchMediaUseCaseImpl: SearchMediaUseCase, KoinComponent {

    private val mediaBaseRepository: MediaBaseRepository by inject()

    override suspend fun invoke(query: String): Flow<List<MediaBaseData>> {
        return mediaBaseRepository.searchMedia(query)
    }
}

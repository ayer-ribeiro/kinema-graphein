package dev.ayer.kinemagraphein.domain.usecase

import dev.ayer.kinemagraphein.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.core.usecase.LoadMoreShowItemsUseCase
import dev.ayer.kinemagraphein.entity.media.ShowBaseData
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class LoadMoreShowItemsUseCaseImpl: LoadMoreShowItemsUseCase, KoinComponent {

    private val mediaBaseRepository: MediaBaseRepository by inject()

    override suspend fun invoke(): Flow<List<ShowBaseData>> {
        return mediaBaseRepository.loadMoreShowItems()
    }
}

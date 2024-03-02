package dev.ayer.kinemagraphein.android.domain

import dev.ayer.kinemagraphein.android.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.android.core.usecase.LoadRecentUseCase
import dev.ayer.kinemagraphein.android.entity.media.MediaBaseData
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoadRecentUseCaseImpl: LoadRecentUseCase, KoinComponent {

    private val mediaBaseRepository: MediaBaseRepository by inject()

    override suspend fun invoke(): Flow<List<MediaBaseData>> {
        return mediaBaseRepository.loadRecentMedia()
    }
}

package dev.ayer.kinemagraphein.android.domain

import dev.ayer.kinemagraphein.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.core.usecase.LoadFavoritesUseCase
import dev.ayer.kinemagraphein.entity.media.MediaBaseData
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoadFavoritesUseCaseImpl: LoadFavoritesUseCase, KoinComponent {

    private val mediaBaseRepository: MediaBaseRepository by inject()

    override suspend fun invoke(): Flow<List<MediaBaseData>> {
        return mediaBaseRepository.loadFavoriteMedia()
    }
}

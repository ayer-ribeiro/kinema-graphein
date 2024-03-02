package dev.ayer.kinemagraphein.android.domain

import dev.ayer.kinemagraphein.android.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.android.core.usecase.ChangeFavoriteStateUseCase
import dev.ayer.kinemagraphein.android.entity.media.MediaBaseData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ChangeFavoriteStateUseCaseImpl: ChangeFavoriteStateUseCase, KoinComponent {

    private val mediaBaseRepository: MediaBaseRepository by inject()

    override suspend fun invoke(media: MediaBaseData): Boolean {
        val isFavorite = media.isFavorite
        if (isFavorite) {
            mediaBaseRepository.removeFromFavorite(media)
            return false
        } else {
            mediaBaseRepository.addAsFavorite(media)
            return true
        }
    }
}

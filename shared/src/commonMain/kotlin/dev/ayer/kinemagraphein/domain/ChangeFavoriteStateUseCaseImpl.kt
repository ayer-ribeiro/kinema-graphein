package dev.ayer.kinemagraphein.domain

import dev.ayer.kinemagraphein.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.core.usecase.ChangeFavoriteStateUseCase
import dev.ayer.kinemagraphein.entity.media.MediaBaseData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class ChangeFavoriteStateUseCaseImpl: ChangeFavoriteStateUseCase, KoinComponent {

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

package dev.ayer.kinemagraphein.domain.usecase

import dev.ayer.kinemagraphein.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.core.usecase.ChangeFavoriteStateUseCase
import dev.ayer.kinemagraphein.entity.media.ShowBase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class ChangeFavoriteStateUseCaseImpl : ChangeFavoriteStateUseCase, KoinComponent {

    private val mediaBaseRepository: MediaBaseRepository by inject()

    override suspend fun invoke(media: ShowBase): Boolean {
        val isFavorite = media.isFavorite
        return if (isFavorite) {
            mediaBaseRepository.removeFromFavorite(media)
            false
        } else {
            mediaBaseRepository.addAsFavorite(media)
            true
        }
    }
}

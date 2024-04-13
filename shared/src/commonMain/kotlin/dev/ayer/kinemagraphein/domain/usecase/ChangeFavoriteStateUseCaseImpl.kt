package dev.ayer.kinemagraphein.domain.usecase

import dev.ayer.kinemagraphein.core.repository.MediaBaseRepository
import dev.ayer.kinemagraphein.core.usecase.ChangeFavoriteStateUseCase
import dev.ayer.kinemagraphein.entity.media.ShowBase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class ChangeFavoriteStateUseCaseImpl : ChangeFavoriteStateUseCase, KoinComponent {

    private val mediaBaseRepository: MediaBaseRepository by inject()

    override suspend fun invoke(media: ShowBase) {
        val isFavorite = media.isFavorite
        if (isFavorite) {
            mediaBaseRepository.removeFromFavorite(media)
        } else {
            mediaBaseRepository.addAsFavorite(media)
        }
    }

    override suspend fun invoke(mediaId: Long) {
        mediaBaseRepository.updateFavoriteState(mediaId)
    }
}

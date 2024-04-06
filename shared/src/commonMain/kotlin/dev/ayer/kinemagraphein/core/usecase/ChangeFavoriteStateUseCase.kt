package dev.ayer.kinemagraphein.core.usecase

import dev.ayer.kinemagraphein.entity.media.ShowBase

interface ChangeFavoriteStateUseCase {
    suspend operator fun invoke(media: ShowBase): Boolean
}

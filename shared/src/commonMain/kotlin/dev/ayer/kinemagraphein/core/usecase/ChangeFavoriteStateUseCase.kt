package dev.ayer.kinemagraphein.core.usecase

import dev.ayer.kinemagraphein.entity.media.MediaBaseData

interface ChangeFavoriteStateUseCase {
    suspend operator fun invoke(media: MediaBaseData): Boolean
}

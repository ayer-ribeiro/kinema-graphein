package dev.ayer.kinemagraphein.core.usecase

import dev.ayer.kinemagraphein.entity.media.ShowBaseData

interface ChangeFavoriteStateUseCase {
    suspend operator fun invoke(media: ShowBaseData): Boolean
}

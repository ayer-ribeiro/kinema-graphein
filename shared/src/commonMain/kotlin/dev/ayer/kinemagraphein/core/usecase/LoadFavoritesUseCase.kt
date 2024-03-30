package dev.ayer.kinemagraphein.core.usecase

import dev.ayer.kinemagraphein.entity.media.ShowBaseData
import kotlinx.coroutines.flow.Flow

interface LoadFavoritesUseCase {
    suspend operator fun invoke(): Flow<List<ShowBaseData>>
}

package dev.ayer.kinemagraphein.core.usecase

import dev.ayer.kinemagraphein.entity.media.Show
import kotlinx.coroutines.flow.Flow

interface FetchSeriesDataUseCase {
    suspend operator fun invoke(id: String): Flow<Show?>
}
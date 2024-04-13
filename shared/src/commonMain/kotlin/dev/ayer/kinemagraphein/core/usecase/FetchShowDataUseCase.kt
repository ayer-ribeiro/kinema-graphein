package dev.ayer.kinemagraphein.core.usecase

import dev.ayer.kinemagraphein.entity.media.Show
import kotlinx.coroutines.flow.Flow

interface FetchShowDataUseCase {
    suspend operator fun invoke(id: Long): Flow<Show?>
}

package dev.ayer.kinemagraphein.core.usecase

import dev.ayer.kinemagraphein.entity.media.ShowBaseData
import kotlinx.coroutines.flow.Flow

interface SearchMediaUseCase {
    suspend operator fun invoke(query: String): Flow<List<ShowBaseData>>
}

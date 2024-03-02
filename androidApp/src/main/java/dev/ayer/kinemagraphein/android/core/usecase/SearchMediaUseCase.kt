package dev.ayer.kinemagraphein.android.core.usecase

import dev.ayer.kinemagraphein.entity.media.MediaBaseData
import kotlinx.coroutines.flow.Flow

interface SearchMediaUseCase {
    suspend operator fun invoke(query: String): Flow<List<MediaBaseData>>
}

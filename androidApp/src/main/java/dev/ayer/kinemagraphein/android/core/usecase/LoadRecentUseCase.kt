package dev.ayer.kinemagraphein.android.core.usecase

import dev.ayer.kinemagraphein.android.entity.media.MediaBaseData
import kotlinx.coroutines.flow.Flow

interface LoadRecentUseCase {
    suspend operator fun invoke(): Flow<List<MediaBaseData>>
}

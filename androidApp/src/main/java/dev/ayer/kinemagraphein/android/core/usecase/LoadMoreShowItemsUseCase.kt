package dev.ayer.kinemagraphein.android.core.usecase

import dev.ayer.kinemagraphein.entity.media.MediaBaseData
import kotlinx.coroutines.flow.Flow

interface LoadMoreShowItemsUseCase {
    suspend operator fun invoke(): Flow<List<MediaBaseData>>
}

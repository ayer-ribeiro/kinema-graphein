package dev.ayer.kinemagraphein.android.presenter.designsystem.media.mediaitemcover

import androidx.compose.runtime.Immutable

@Immutable
data class MediaItemCoverUiState(
    val id: Long,
    val title: String,
    val isFavorite: Boolean,
    val imageUrl: String?,
)
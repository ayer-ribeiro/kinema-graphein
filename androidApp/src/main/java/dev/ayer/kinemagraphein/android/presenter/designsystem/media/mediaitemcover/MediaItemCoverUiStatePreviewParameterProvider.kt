package dev.ayer.kinemagraphein.android.presenter.designsystem.media.mediaitemcover

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class MediaItemCoverUiStatePreviewParameterProvider: PreviewParameterProvider<MediaItemCoverUiState> {
    override val values: Sequence<MediaItemCoverUiState>
        get() = sequenceOf(
            MediaItemCoverUiState(
                id = 1,
                title = "Supernatural",
                imageUrl = "",
                isFavorite = true
            ),
            MediaItemCoverUiState(
                id = 2,
                title = "Breaking Bad",
                imageUrl = "",
                isFavorite = false
            ),
            MediaItemCoverUiState(
                id = 3,
                title = "The Amazing Race",
                imageUrl = "",
                isFavorite = true
            )
        )
}
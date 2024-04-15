package dev.ayer.kinemagraphein.android.presenter.designsystem.media.mediaitemcover

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

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

class MediaItemCoverUiStateListPreviewParameterProvider: PreviewParameterProvider<ImmutableList<MediaItemCoverUiState>> {
    override val values: Sequence<ImmutableList<MediaItemCoverUiState>>
        get() = sequenceOf(
            listOf(
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
            ).toImmutableList()
        )
}
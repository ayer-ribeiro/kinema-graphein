package dev.ayer.kinemagraphein.android.presenter.screens.home

import androidx.compose.runtime.Immutable
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.mediaitemcover.MediaItemCoverUiState
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class SearchBarState(
    val searchResult: ImmutableList<MediaItemCoverUiState>,
    val searchQuery: String,
    val searchActiveState: Boolean
)
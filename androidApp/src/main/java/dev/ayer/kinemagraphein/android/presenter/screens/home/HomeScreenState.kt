package dev.ayer.kinemagraphein.android.presenter.screens.home

import dev.ayer.kinemagraphein.android.presenter.designsystem.media.mediaitemcover.MediaItemCoverUiState
import kotlinx.collections.immutable.ImmutableList

data class HomeScreenState(
    val isLoading: Boolean,
    val isLoadingMoreItems: Boolean,
    val hasError: Boolean,
    val favorites: ImmutableList<MediaItemCoverUiState>,
    val recent: ImmutableList<MediaItemCoverUiState>,
    val allMediaList: ImmutableList<MediaItemCoverUiState>
)

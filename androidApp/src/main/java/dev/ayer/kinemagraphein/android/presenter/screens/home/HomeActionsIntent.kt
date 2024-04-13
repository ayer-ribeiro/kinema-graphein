package dev.ayer.kinemagraphein.android.presenter.screens.home

import androidx.compose.runtime.Immutable
import dev.ayer.kinemagraphein.android.presenter.actions.ViewIntentActions
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.mediaitemcover.MediaItemCoverUiState

@Immutable
sealed class HomeActionsIntent: ViewIntentActions {
    data class FavoriteClicked(val media: MediaItemCoverUiState): HomeActionsIntent()
    data class MediaClicked(val media: MediaItemCoverUiState): HomeActionsIntent()

    data object LoadMoreItems: HomeActionsIntent()

    data class SearchQueryChanged(val query: String): HomeActionsIntent()
    data object SearchQueryCleared: HomeActionsIntent()
    data class SearchCalled(val query: String): HomeActionsIntent()
    data class SearchActiveStateChanged(val isActive: Boolean): HomeActionsIntent()
}

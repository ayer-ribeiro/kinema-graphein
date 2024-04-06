package dev.ayer.kinemagraphein.android.presenter.screens.home

import dev.ayer.kinemagraphein.android.presenter.actions.ViewIntentActions
import dev.ayer.kinemagraphein.entity.media.ShowBase

sealed class HomeActionsIntent: ViewIntentActions {
    class FavoriteClicked(val media: ShowBase): HomeActionsIntent()
    class MediaClicked(val media: ShowBase): HomeActionsIntent()

    data object LoadMoreItems: HomeActionsIntent()

    class SearchQueryChanged(val query: String): HomeActionsIntent()
    data object SearchQueryCleared: HomeActionsIntent()
    class SearchCalled(val query: String): HomeActionsIntent()
    class SearchActiveStateChanged(val isActive: Boolean): HomeActionsIntent()
}

package dev.ayer.kinemagraphein.presenter.home

import dev.ayer.kinemagraphein.entity.media.MediaBaseData
import dev.ayer.kinemagraphein.presenter.actions.ViewIntentActions

sealed class HomeActionsIntent: ViewIntentActions {
    class FavoriteClicked(val media: MediaBaseData): HomeActionsIntent()
    class MediaClicked(val media: MediaBaseData): HomeActionsIntent()

    data object LoadMoreItems: HomeActionsIntent()

    class SearchQueryChanged(val query: String): HomeActionsIntent()
    data object SearchQueryCleared: HomeActionsIntent()
    class SearchCalled(val query: String): HomeActionsIntent()
    class SearchActiveStateChanged(val isActive: Boolean): HomeActionsIntent()
}

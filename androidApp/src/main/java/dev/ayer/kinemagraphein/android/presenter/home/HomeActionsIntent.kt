package dev.ayer.kinemagraphein.android.presenter.home

import dev.ayer.kinemagraphein.entity.media.ShowBaseData
import dev.ayer.kinemagraphein.android.presenter.actions.ViewIntentActions

sealed class HomeActionsIntent: ViewIntentActions {
    class FavoriteClicked(val media: ShowBaseData): HomeActionsIntent()
    class MediaClicked(val media: ShowBaseData): HomeActionsIntent()

    data object LoadMoreItems: HomeActionsIntent()

    class SearchQueryChanged(val query: String): HomeActionsIntent()
    data object SearchQueryCleared: HomeActionsIntent()
    class SearchCalled(val query: String): HomeActionsIntent()
    class SearchActiveStateChanged(val isActive: Boolean): HomeActionsIntent()
}

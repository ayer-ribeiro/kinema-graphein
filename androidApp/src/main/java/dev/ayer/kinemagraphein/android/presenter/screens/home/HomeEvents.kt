package dev.ayer.kinemagraphein.android.presenter.screens.home

sealed class HomeEvents {
    sealed class Navigation: HomeEvents() {
        data class NavigateToShowDetails(val mediaId: Long): Navigation()
        data object NavigateToFavoritesList: Navigation()
    }
}

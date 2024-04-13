package dev.ayer.kinemagraphein.android.presenter.screens.home

sealed class HomeEvents {
    sealed class Navigation: HomeEvents() {
        class NavigateToShowDetails(val mediaId: Long): Navigation()
    }
}

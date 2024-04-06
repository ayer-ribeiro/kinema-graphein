package dev.ayer.kinemagraphein.android.presenter.screens.home

sealed class HomeEvents {
    sealed class Navigation: HomeEvents() {
        class NavigateToSeriesDetails(val mediaId: String): Navigation()
    }
}

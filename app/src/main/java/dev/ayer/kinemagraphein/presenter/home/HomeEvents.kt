package dev.ayer.kinemagraphein.presenter.home

sealed class HomeEvents {
    sealed class Navigation: HomeEvents() {
        class NavigateToSeriesDetails(val mediaId: String): Navigation()
    }
}
package dev.ayer.kinemagraphein.android.presenter.home

sealed class HomeEvents {
    sealed class Navigation: HomeEvents() {
        class NavigateToSeriesDetails(val mediaId: String): Navigation()
    }
}

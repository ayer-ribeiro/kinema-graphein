package dev.ayer.kinemagraphein.android.presenter.screens.show

sealed class ShowEvents {
    sealed class Navigation: ShowEvents() {
        class NavigateToEpisodeDetails(val episode: ShowScreenEpisodeData): Navigation()
    }
}

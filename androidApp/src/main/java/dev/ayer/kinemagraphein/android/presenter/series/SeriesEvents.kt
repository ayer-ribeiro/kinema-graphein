package dev.ayer.kinemagraphein.android.presenter.series

import dev.ayer.kinemagraphein.entity.media.Episode

sealed class SeriesEvents {
    sealed class Navigation: SeriesEvents() {
        class NavigateToEpisodeDetails(val episode: Episode): Navigation()
    }
}

package dev.ayer.kinemagraphein.android.presenter.episode

import dev.ayer.kinemagraphein.entity.media.Episode

data class EpisodeScreenState(
    val isLoading: Boolean = false,
    val isOnError: Boolean = false,
    val episode: Episode? = null
)

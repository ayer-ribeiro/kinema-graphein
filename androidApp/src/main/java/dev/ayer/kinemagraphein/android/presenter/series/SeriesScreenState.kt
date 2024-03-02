package dev.ayer.kinemagraphein.android.presenter.series

import dev.ayer.kinemagraphein.android.entity.media.Show

data class SeriesScreenState(
    val isLoading: Boolean,
    val isError: Boolean,
    val show: Show? = null
)

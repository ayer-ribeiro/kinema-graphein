package dev.ayer.kinemagraphein.android.presenter.screens.home

import dev.ayer.kinemagraphein.entity.media.ShowBase
import kotlinx.collections.immutable.ImmutableList

data class SearchBarState(
    val searchResult: ImmutableList<ShowBase>,
    val searchQuery: String,
    val searchActiveState: Boolean
)
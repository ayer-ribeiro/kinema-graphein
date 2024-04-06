package dev.ayer.kinemagraphein.android.presenter.screens.home

import dev.ayer.kinemagraphein.entity.media.ShowBase
import kotlinx.collections.immutable.ImmutableList

data class HomeScreenState(
    val isLoading: Boolean,
    val isLoadingMoreItems: Boolean,
    val isError: Boolean,
    val favorites: ImmutableList<ShowBase>,
    val recent: ImmutableList<ShowBase>,
    val allMediaList: ImmutableList<ShowBase>,
    val searchResult: ImmutableList<ShowBase>,
    val searchQuery: String,
    val searchActiveState: Boolean
)

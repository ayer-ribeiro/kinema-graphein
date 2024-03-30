package dev.ayer.kinemagraphein.android.presenter.home

import dev.ayer.kinemagraphein.entity.media.ShowBaseData
import kotlinx.collections.immutable.ImmutableList

data class HomeScreenState(
    val isLoading: Boolean,
    val isLoadingMoreItems: Boolean,
    val isError: Boolean,
    val favorites: ImmutableList<ShowBaseData>,
    val recent: ImmutableList<ShowBaseData>,
    val allMediaList: ImmutableList<ShowBaseData>,
    val searchResult: ImmutableList<ShowBaseData>,
    val searchQuery: String,
    val searchActiveState: Boolean
)

package dev.ayer.kinemagraphein.android.presenter.home

import dev.ayer.kinemagraphein.entity.media.MediaBaseData

data class HomeScreenState(
    val isLoading: Boolean,
    val isLoadingMoreItems: Boolean,
    val isError: Boolean,
    val favorites: List<MediaBaseData>,
    val recent: List<MediaBaseData>,
    val allMediaList: List<MediaBaseData>,
    val searchResult: List<MediaBaseData>,
    val searchQuery: String,
    val searchActiveState: Boolean
)

package dev.ayer.kinemagraphein.android.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ayer.kinemagraphein.core.usecase.ChangeFavoriteStateUseCase
import dev.ayer.kinemagraphein.core.usecase.LoadFavoritesUseCase
import dev.ayer.kinemagraphein.core.usecase.LoadMoreShowItemsUseCase
import dev.ayer.kinemagraphein.core.usecase.LoadRecentUseCase
import dev.ayer.kinemagraphein.core.usecase.SearchMediaUseCase
import dev.ayer.kinemagraphein.data.api.adapter.withNewFavoriteState
import dev.ayer.kinemagraphein.entity.media.ShowBaseData
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

typealias State = HomeScreenState

class HomeViewModel : ViewModel(), KoinComponent {

    private val changeFavoriteState: ChangeFavoriteStateUseCase by inject()
    private val loadRecent: LoadRecentUseCase by inject()
    private val loadFavorites: LoadFavoritesUseCase by inject()
    private val searchMedia: SearchMediaUseCase by inject()
    private val loadSeriesList: LoadMoreShowItemsUseCase by inject()

    private val emptyState
        get() = State(
            isLoading = false,
            isLoadingMoreItems = false,
            isError = false,
            favorites = persistentListOf(),
            recent = persistentListOf(),
            allMediaList = persistentListOf(),
            searchResult = persistentListOf(),
            searchQuery = "",
            searchActiveState = false
        )

    private val _uiState = MutableStateFlow(emptyState)
    val uiState: StateFlow<State> = _uiState

    private val _uiEvents = MutableSharedFlow<HomeEvents>()
    val uiEvents: Flow<HomeEvents> = _uiEvents

    init {
        collectRecent()
        collectFavorite()
        loadInitialData()
    }

    fun onAction(homeActions: HomeActionsIntent) {
        when (homeActions) {
            is HomeActionsIntent.FavoriteClicked -> onFavoriteClicked(homeActions.media)
            is HomeActionsIntent.MediaClicked -> onMediaClicked(homeActions.media)
            is HomeActionsIntent.SearchActiveStateChanged -> onSearchActiveStateChanged(homeActions.isActive)
            is HomeActionsIntent.SearchQueryChanged -> onSearchQueryChanged(homeActions.query)
            is HomeActionsIntent.SearchCalled -> onSearchCalled(homeActions.query)
            is HomeActionsIntent.SearchQueryCleared -> onSearchQueryCleared()
            is HomeActionsIntent.LoadMoreItems -> loadMoreItems()
        }
    }

    private fun loadMoreItems() = viewModelScope.launch {
        if (uiState.value.isLoadingMoreItems) return@launch

        val loadingState = _uiState.value.copy(isLoadingMoreItems = true)
        _uiState.emit(loadingState)

        delay(500L)

        val items = loadSeriesList().firstOrNull() ?: emptyList()
        val newState = _uiState.value.copy(
            allMediaList = items.toPersistentList(),
            isLoadingMoreItems = false
        )
        _uiState.emit(newState)
    }

    private fun onSearchQueryCleared() = viewModelScope.launch {
        val newState = _uiState.value.copy(
            searchResult = persistentListOf(),
            searchQuery = "",
            searchActiveState = false,
        )
        _uiState.emit(newState)
    }

    private fun onSearchCalled(query: String) = viewModelScope.launch {
        val searchResult = searchMedia(query).firstOrNull() ?: emptyList()
        val newState = _uiState.value.copy(
            searchResult = searchResult.toPersistentList(),
        )
        _uiState.emit(newState)
    }

    private fun onSearchQueryChanged(query: String) = viewModelScope.launch {
        val newState = _uiState.value.copy(
            searchQuery = query,
        )
        _uiState.emit(newState)
    }

    private fun onSearchActiveStateChanged(isActive: Boolean) = viewModelScope.launch {
        val newState = _uiState.value.copy(
            searchActiveState = isActive,
        )
        _uiState.emit(newState)
    }

    private fun onMediaClicked(media: ShowBaseData) = viewModelScope.launch {
        val event = HomeEvents.Navigation.NavigateToSeriesDetails(media.id)
        _uiEvents.emit(event)
    }

    private fun onFavoriteClicked(
        media: ShowBaseData,
    ) = viewModelScope.launch {
        val newFavoriteState = !media.isFavorite
        changeFavoriteState(media)

        val favorites = _uiState.value.favorites.withNewFavoriteState(media, newFavoriteState)
        val recent = _uiState.value.recent.withNewFavoriteState(media, newFavoriteState)
        val allMediaList = _uiState.value.allMediaList .withNewFavoriteState(media, newFavoriteState)

        val state = _uiState.value.copy(
            favorites = favorites.toPersistentList(),
            recent = recent.toPersistentList(),
            allMediaList = allMediaList.toPersistentList(),
        )
        _uiState.emit(state)
    }

    private fun collectFavorite() {
        viewModelScope.launch {
            loadFavorites().collect { favorite ->
                val newFavoriteState = _uiState.value.copy(
                    favorites = favorite.toImmutableList()
                )
                _uiState.emit(newFavoriteState)
            }
        }
    }

    private fun collectRecent() {
        viewModelScope.launch {
            loadRecent().collect { recent ->
                val newRecentState = _uiState.value.copy(
                    recent = recent.toImmutableList()
                )
                _uiState.emit(newRecentState)
            }
        }
    }

    private fun loadInitialData() = viewModelScope.launch {
        val loadingState = _uiState.value.copy(
            isLoading = true
        )

        _uiState.emit(loadingState)

        val firstState = _uiState.value.copy(
            favorites = loadFavorites().firstOrNull()?.toPersistentList() ?: persistentListOf(),
            recent = loadRecent().firstOrNull()?.toPersistentList() ?: persistentListOf(),
            allMediaList = loadSeriesList().firstOrNull()?.toPersistentList() ?: persistentListOf(),
            isLoading = false,
            isLoadingMoreItems = false,
        )
        _uiState.emit(firstState)
    }

    private fun List<ShowBaseData>.withNewFavoriteState(
        media: ShowBaseData,
        favoriteState: Boolean
    ): List<ShowBaseData> {
        return this.map {
            if (it.id != media.id) {
                return@map it
            }
            return@map it.withNewFavoriteState(isFavorite = favoriteState)
        }
    }
}

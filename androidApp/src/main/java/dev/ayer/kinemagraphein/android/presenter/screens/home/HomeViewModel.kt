package dev.ayer.kinemagraphein.android.presenter.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ayer.kinemagraphein.core.usecase.ChangeFavoriteStateUseCase
import dev.ayer.kinemagraphein.core.usecase.LoadFavoritesUseCase
import dev.ayer.kinemagraphein.core.usecase.LoadMoreShowItemsUseCase
import dev.ayer.kinemagraphein.core.usecase.LoadRecentUseCase
import dev.ayer.kinemagraphein.core.usecase.SearchMediaUseCase
import dev.ayer.kinemagraphein.entity.media.ShowBase
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
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

    private val initialState
        get() = State(
            isLoading = true,
            isLoadingMoreItems = false,
            isError = false,
            favorites = persistentListOf(),
            recent = persistentListOf(),
            allMediaList = persistentListOf(),
            searchResult = persistentListOf(),
            searchQuery = "",
            searchActiveState = false
        )

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState

    private val _uiEvents = MutableSharedFlow<HomeEvents>()
    val uiEvents: Flow<HomeEvents> = _uiEvents

    init {
        collectRecent()
        collectFavorite()
        collectShows()
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
        emitLoadingMoreItemsState()
        loadSeriesList()
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

    private fun onMediaClicked(media: ShowBase) = viewModelScope.launch {
        val event = HomeEvents.Navigation.NavigateToSeriesDetails(media.id)
        _uiEvents.emit(event)
    }

    private fun onFavoriteClicked(
        media: ShowBase,
    ) = viewModelScope.launch {
        changeFavoriteState(media)
    }

    private suspend fun emitLoadingMoreItemsState() {
        val loadingState = _uiState.value.copy(isLoadingMoreItems = true)
        _uiState.emit(loadingState)
    }

    private fun collectFavorite() = viewModelScope.launch {
        loadFavorites().collect { favorite ->
            val newFavoriteState = _uiState.value.copy(
                favorites = favorite.toImmutableList()
            )
            _uiState.emit(newFavoriteState)
        }
    }

    private fun collectRecent() = viewModelScope.launch {
        loadRecent().collect { recent ->
            val newRecentState = _uiState.value.copy(
                recent = recent.toImmutableList()
            )
            _uiState.emit(newRecentState)
        }
    }

    private fun collectShows() = viewModelScope.launch {
        loadSeriesList().collect {
            val newState = _uiState.value.copy(
                allMediaList = it.toImmutableList(),
                isLoading = false,
                isLoadingMoreItems = false,
            )
            _uiState.emit(newState)
        }
    }
}

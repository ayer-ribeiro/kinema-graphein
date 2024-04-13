package dev.ayer.kinemagraphein.android.presenter.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ayer.kinemagraphein.android.presenter.adapter.asMediaItemCoverUiState
import dev.ayer.kinemagraphein.android.presenter.designsystem.media.mediaitemcover.MediaItemCoverUiState
import dev.ayer.kinemagraphein.core.usecase.ChangeFavoriteStateUseCase
import dev.ayer.kinemagraphein.core.usecase.LoadFavoritesUseCase
import dev.ayer.kinemagraphein.core.usecase.LoadMoreShowItemsUseCase
import dev.ayer.kinemagraphein.core.usecase.LoadRecentUseCase
import dev.ayer.kinemagraphein.core.usecase.SearchMediaUseCase
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
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
    private val loadShowsList: LoadMoreShowItemsUseCase by inject()

    private val emptySearchBarState = SearchBarState(
        searchResult = persistentListOf(),
        searchQuery = "",
        searchActiveState = false,
    )

    private val initialState
        get() = State(
            isLoading = true,
            isLoadingMoreItems = false,
            hasError = false,
            favorites = persistentListOf(),
            recent = persistentListOf(),
            allMediaList = persistentListOf(),
        )

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState

    private val _uiSearchBarState = MutableStateFlow(emptySearchBarState)
    val uiSearchBarState: StateFlow<SearchBarState> = _uiSearchBarState

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

    private fun loadMoreItems() = viewModelScope.launch(Dispatchers.IO) {
        if (uiState.value.isLoadingMoreItems) return@launch
        emitLoadingMoreItemsState()
        collectShows()
    }

    private fun onSearchQueryCleared() = viewModelScope.launch(Dispatchers.Default) {
        _uiSearchBarState.emit(emptySearchBarState)
    }

    private fun onSearchCalled(query: String) = viewModelScope.launch(Dispatchers.IO) {
        val searchResult = searchMedia(query).firstOrNull() ?: emptyList()

        val newState = _uiSearchBarState.value.copy(
            searchResult = searchResult
                .map { it.asMediaItemCoverUiState() }
                .toImmutableList()
        )
        _uiSearchBarState.emit(newState)
    }

    private fun onSearchQueryChanged(query: String) = viewModelScope.launch(Dispatchers.Default) {
        val newState = _uiSearchBarState.value.copy(
            searchQuery = query,
        )
        _uiSearchBarState.emit(newState)
    }

    private fun onSearchActiveStateChanged(isActive: Boolean) = viewModelScope.launch(Dispatchers.Default) {
        val newState = _uiSearchBarState.value.copy(
            searchActiveState = isActive,
        )
        _uiSearchBarState.emit(newState)
    }

    private fun onMediaClicked(media: MediaItemCoverUiState) = viewModelScope.launch(Dispatchers.Default) {
        val event = HomeEvents.Navigation.NavigateToShowDetails(media.id)
        _uiEvents.emit(event)
    }

    private fun onFavoriteClicked(
        media: MediaItemCoverUiState,
    ) = viewModelScope.launch((Dispatchers.IO)) {
        changeFavoriteState(media.id)
    }

    private suspend fun emitLoadingMoreItemsState() {
        val loadingState = _uiState.value.copy(isLoadingMoreItems = true)
        _uiState.emit(loadingState)
    }

    private fun collectFavorite() = viewModelScope.launch(Dispatchers.IO) {
        loadFavorites().collect { favorite ->
            val newFavoriteState = _uiState.value.copy(
                favorites = favorite.map { it.asMediaItemCoverUiState() }.toImmutableList()
            )
            _uiState.emit(newFavoriteState)
        }
    }

    private fun collectRecent() = viewModelScope.launch(Dispatchers.IO) {
        loadRecent().collect { recent ->
            val newRecentState = _uiState.value.copy(
                recent = recent
                    .map { it.asMediaItemCoverUiState() }
                    .toImmutableList()
            )
            _uiState.emit(newRecentState)
        }
    }

    private fun collectShows() = viewModelScope.launch(Dispatchers.IO) {
        loadShowsList().collect { loadedShows ->
            val newState = _uiState.value.copy(
                allMediaList = loadedShows
                    .map { it.asMediaItemCoverUiState() }
                    .toImmutableList(),
                isLoading = false,
                isLoadingMoreItems = false,
            )
            _uiState.emit(newState)
        }
    }
}

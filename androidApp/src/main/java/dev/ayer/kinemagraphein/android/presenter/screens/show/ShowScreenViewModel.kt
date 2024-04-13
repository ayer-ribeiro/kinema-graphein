package dev.ayer.kinemagraphein.android.presenter.screens.show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ayer.kinemagraphein.core.usecase.ChangeFavoriteStateUseCase
import dev.ayer.kinemagraphein.core.usecase.FetchShowDataUseCase
import dev.ayer.kinemagraphein.entity.media.Season
import dev.ayer.kinemagraphein.entity.media.Show
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

typealias State = ShowScreenState

class ShowScreenViewModel(
    private val showId: Long
) : ViewModel(), KoinComponent {

    private val fetchShowData: FetchShowDataUseCase by inject()
    private val changeFavoriteState: ChangeFavoriteStateUseCase by inject()

    private val emptyState
        get() = State(
            isLoading = false,
            isError = false,
            showScreenData = null,
            seasons = emptyList<ShowScreenSeasonData>().toImmutableList(),
            currentSeason = null
        )

    private val _uiState = MutableStateFlow(emptyState)
    val uiState: StateFlow<State> = _uiState

    private val _uiEvents = MutableSharedFlow<ShowEvents>()
    val uiEvents: Flow<ShowEvents> = _uiEvents

    init {
        loadInitialData()
    }

    private fun loadInitialData() = viewModelScope.launch {
        val loadingState = _uiState.value.copy(isLoading = true)
        _uiState.emit(loadingState)

        fetchShowData(showId).collect { show ->
            if (show == null) {
                val newState = _uiState.value.copy(
                    isLoading = false,
                    isError = true
                )
                _uiState.emit(newState)
                return@collect
            }

            val showScreenData = show.asShowScreenData()

            val seasons = show.seasons
                .map { it.asShowScreenSeasonData() }
                .toImmutableList()

            val newState = _uiState.value.copy(
                showScreenData = showScreenData,
                seasons = seasons,
                currentSeason = seasons.firstOrNull(),
                isLoading = false
            )
            _uiState.emit(newState)
        }
    }

    fun onSelect(season: ShowScreenSeasonData) = viewModelScope.launch {
        val newState = _uiState.value.copy(
            currentSeason = season,
        )
        _uiState.emit(newState)
    }

    fun onSelect(episode: ShowScreenEpisodeData) = viewModelScope.launch {
        _uiEvents.emit(
            ShowEvents.Navigation.NavigateToEpisodeDetails(episode)
        )
    }

    fun onFavoriteClick(show: ShowScreenData) = viewModelScope.launch {
        changeFavoriteState(show.id)
    }

    private fun Season.asShowScreenSeasonData() =
        ShowScreenSeasonData(
            number = this.number,
            episodes = this.episodes.map { episode ->
                ShowScreenEpisodeData(
                    name = episode.name,
                    summary = episode.summary,
                    releaseDate = episode.releaseDate,
                    number = episode.number,
                    season = episode.season,
                    imageUrl = episode.mediumImageUrl,
                )
            }.toImmutableList()
        )

    private fun Show.asShowScreenData() = ShowScreenData(
        id = this.id,
        isFavorite = this.isFavorite,
        summary = this.summary,
        releaseDate = this.releaseDate,
        name = this.name,
        imageUrl = this.originalImageUrl,
        genres = this.genres.toImmutableList(),
        timeSchedule = this.schedule.time,
        weekdays = this.schedule.weekdays.toImmutableList()
    )
}

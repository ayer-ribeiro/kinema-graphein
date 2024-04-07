package dev.ayer.kinemagraphein.android.presenter.screens.show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ayer.kinemagraphein.android.presenter.screens.home.HomeEvents
import dev.ayer.kinemagraphein.core.usecase.FetchSeriesDataUseCase
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

typealias State = ShowScreenState

class ShowScreenViewModel(
    private val seriesId: String
): ViewModel(), KoinComponent {

    val fetchSeriesData: FetchSeriesDataUseCase by inject()

    private val emptyState get() = State(
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

        val series = fetchSeriesData(seriesId).firstOrNull()
        if (series == null) {
            val newState = _uiState.value.copy(
                isLoading = false,
                isError = true
            )
            _uiState.emit(newState)
            return@launch
        }

        val showScreenData = ShowScreenData(
            id = series.id,
            isFavorite = series.isFavorite,
            summary = series.summary,
            releaseDate = series.releaseDate,
            name = series.name,
            imageUrl = series.originalImageUrl,
            genres = series.genres.toImmutableList(),
            timeSchedule = series.schedule.time,
            weekdays = series.schedule.weekdays.toImmutableList()
        )

        val seasons = series.seasons.map { season ->
            ShowScreenSeasonData(
                number = season.number,
                episodes = season.episodes.map { episode ->
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
        }.toImmutableList()

        val newState = _uiState.value.copy(
            showScreenData = showScreenData,
            seasons = seasons,
            currentSeason = seasons.firstOrNull(),
            isLoading = false
        )
        _uiState.emit(newState)
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
}

package dev.ayer.kinemagraphein.android.presenter.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ayer.kinemagraphein.core.usecase.FetchEpisodeDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

typealias State = EpisodeScreenState

class EpisodeViewModel(
    private val seriesId: String,
    private val season: Int,
    private val number: Int
): ViewModel(), KoinComponent {

    private val fetchEpisodeData: FetchEpisodeDataUseCase by inject()

    private val emptyState get() = State(
        isLoading = false,
        isOnError = false,
        episode = null
    )

    private val _uiState = MutableStateFlow(emptyState)
    val uiState: StateFlow<State> = _uiState

    init {
        loadInitialData()
    }

    private fun loadInitialData() = viewModelScope.launch {
        val episode = fetchEpisodeData(
            seriesId = seriesId,
            season = season,
            number = number
        ).firstOrNull()

        val newState = _uiState.value.copy(
            episode = episode
        )

        _uiState.emit(newState)
    }
}

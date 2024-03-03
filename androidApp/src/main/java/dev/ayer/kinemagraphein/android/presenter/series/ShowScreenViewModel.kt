package dev.ayer.kinemagraphein.android.presenter.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ayer.kinemagraphein.core.usecase.FetchSeriesDataUseCase
import dev.ayer.kinemagraphein.android.presenter.home.HomeEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

typealias State = SeriesScreenState

class ShowScreenViewModel(
    private val seriesId: String
): ViewModel(), KoinComponent {

    val fetchSeriesData: FetchSeriesDataUseCase by inject()

    private val emptyState get() = State(
        isLoading = false,
        isError = false,
        show = null,
    )

    private val _uiState = MutableStateFlow(emptyState)
    val uiState: StateFlow<State> = _uiState

    private val _uiEvents = MutableSharedFlow<HomeEvents>()
    val uiEvents: Flow<HomeEvents> = _uiEvents

    init {
        loadInitialData()
    }

    private fun loadInitialData() = viewModelScope.launch {
        val loadingState = _uiState.value.copy(isLoading = true)
        _uiState.emit(loadingState)
        delay(1000L)

        val series = fetchSeriesData(seriesId).firstOrNull()
        val newState = _uiState.value.copy(
            show = series,
            isLoading = false
        )
        _uiState.emit(newState)
    }
}

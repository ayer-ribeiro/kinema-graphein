package dev.ayer.kinemagraphein.android.presenter.screens.show.tooling

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.ayer.kinemagraphein.android.presenter.screens.show.ShowScreenState

class ShowScreenStatePreviewParameterProvider : PreviewParameterProvider<ShowScreenState> {
    private val showDataPreviewParameterProvider = ShowDataPreviewParameterProvider()
    private val showScreenSeasonPreviewParameterProvider =
        ShowScreenSeasonPreviewParameterProvider()
    override val values: Sequence<ShowScreenState>
        get() = sequenceOf(
            ShowScreenState(
                isLoading = false,
                isError = false,
                showScreenData = showDataPreviewParameterProvider.values.first(),
                seasons = showScreenSeasonPreviewParameterProvider.values.first(),
                currentSeason = showScreenSeasonPreviewParameterProvider.values.first().first()
            )
        )
}
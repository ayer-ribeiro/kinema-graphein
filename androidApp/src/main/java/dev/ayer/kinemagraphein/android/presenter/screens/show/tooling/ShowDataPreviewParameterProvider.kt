package dev.ayer.kinemagraphein.android.presenter.screens.show.tooling

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.ayer.kinemagraphein.android.presenter.screens.show.ShowScreenData
import kotlinx.collections.immutable.toImmutableList

class ShowDataPreviewParameterProvider : PreviewParameterProvider<ShowScreenData> {
    private val showScreenData = ShowScreenData(
        id = 123,
        isFavorite = false,
        summary = "Now, the list will remain static until the app bar is completely collapsed since the app bar offset is consuming the whole delta and there’s nothing left for the list to use.",
        releaseDate = "30 fev",
        name = "Dexter",
        imageUrl = "https://",
        genres = listOf("Drama", "Adventure").toImmutableList(),
        timeSchedule = "15:30",
        weekdays = listOf("Sunday", "Friday").toImmutableList()
    )
    override val values: Sequence<ShowScreenData>
        get() = sequenceOf(showScreenData)
}

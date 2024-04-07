package dev.ayer.kinemagraphein.android.presenter.designsystem.media

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.ayer.kinemagraphein.entity.media.Show
import dev.ayer.kinemagraphein.entity.media.ShowBase
import dev.ayer.kinemagraphein.entity.media.asShowBase
import dev.ayer.kinemagraphein.mock.personOfInterestMediaMock
import dev.ayer.kinemagraphein.mock.underTheDomeMediaMock

class ShowPreviewParameterProvider : PreviewParameterProvider<Show> {
    override val values = sequenceOf(
        personOfInterestMediaMock,
        underTheDomeMediaMock,
    )
}


class ShowBasePreviewParameterProvider : PreviewParameterProvider<ShowBase> {
    override val values = sequenceOf(
        personOfInterestMediaMock,
        underTheDomeMediaMock,
    ).map { it.asShowBase() }
}

class ShowBaseListPreviewParameterProvider : PreviewParameterProvider<List<ShowBase>> {
    override val values = sequenceOf(
        listOf(
            personOfInterestMediaMock,
            underTheDomeMediaMock,
            personOfInterestMediaMock,
            underTheDomeMediaMock,
            personOfInterestMediaMock,
            underTheDomeMediaMock,
            personOfInterestMediaMock,
            underTheDomeMediaMock,
            personOfInterestMediaMock,
            underTheDomeMediaMock,
            personOfInterestMediaMock,
            underTheDomeMediaMock,
            personOfInterestMediaMock,
            underTheDomeMediaMock,
            personOfInterestMediaMock,
            underTheDomeMediaMock,
            personOfInterestMediaMock,
            underTheDomeMediaMock,
            personOfInterestMediaMock,
            underTheDomeMediaMock,
        ).map { it.asShowBase() }
    )
}
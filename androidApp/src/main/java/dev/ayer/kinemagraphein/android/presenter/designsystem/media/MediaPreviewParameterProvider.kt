package dev.ayer.kinemagraphein.android.presenter.designsystem.media

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.ayer.kinemagraphein.entity.media.ShowBaseData
import dev.ayer.kinemagraphein.mock.personOfInterestMediaMock
import dev.ayer.kinemagraphein.mock.underTheDomeMediaMock

class MediaPreviewParameterProvider: PreviewParameterProvider<ShowBaseData> {
    override val values = sequenceOf(
        personOfInterestMediaMock,
        underTheDomeMediaMock,
    )
}

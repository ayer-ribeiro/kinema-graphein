package dev.ayer.kinemagraphein.android.presenter.designsystem.media

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.ayer.kinemagraphein.entity.media.Media
import dev.ayer.kinemagraphein.entity.media.MediaBaseData
import dev.ayer.kinemagraphein.android.mock.personOfInterestMediaMock
import dev.ayer.kinemagraphein.android.mock.underTheDomeMediaMock

class MediaPreviewParameterProvider: PreviewParameterProvider<MediaBaseData> {
    override val values = sequenceOf(
        personOfInterestMediaMock,
        underTheDomeMediaMock,
    )
}

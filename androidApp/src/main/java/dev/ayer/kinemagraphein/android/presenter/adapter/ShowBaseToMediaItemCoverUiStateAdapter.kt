package dev.ayer.kinemagraphein.android.presenter.adapter

import dev.ayer.kinemagraphein.android.presenter.designsystem.media.mediaitemcover.MediaItemCoverUiState
import dev.ayer.kinemagraphein.entity.media.ShowBase

fun ShowBase.asMediaItemCoverUiState() = MediaItemCoverUiState(
    id = this.id,
    title = this.name,
    isFavorite = this.isFavorite,
    imageUrl = this.mediumImageUrl
)
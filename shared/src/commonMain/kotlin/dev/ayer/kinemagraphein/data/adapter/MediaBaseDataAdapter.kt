package dev.ayer.kinemagraphein.data.adapter

import dev.ayer.kinemagraphein.data.dto.ShowModelBase
import dev.ayer.kinemagraphein.entity.media.MediaBaseData

private data class MediaBaseDataImpl(
    override val id: String,
    override val isFavorite: Boolean,
    override val name: String,
    override val mediumImageUrl: String?,
    override val originalImageUrl: String?,
    override val summary: String?
) : MediaBaseData

fun ShowModelBase.toMediaBaseData(isFavorite: Boolean): MediaBaseData = MediaBaseDataImpl(
    id = id.toString(),
    name = name,
    isFavorite = isFavorite,
    mediumImageUrl = image?.medium,
    originalImageUrl = image?.original,
    summary = summary
)

fun MediaBaseData.withNewFavoriteState(isFavorite: Boolean): MediaBaseData {
    return MediaBaseDataImpl(
        id = id,
        name = name,
        isFavorite = isFavorite,
        mediumImageUrl = mediumImageUrl,
        originalImageUrl = originalImageUrl,
        summary = summary
    )
}

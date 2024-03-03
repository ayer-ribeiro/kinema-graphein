package dev.ayer.kinemagraphein.android.data.adapter

import dev.ayer.kinemagraphein.android.data.sources.room.entity.FavoritesTable
import dev.ayer.kinemagraphein.android.data.sources.room.entity.RecentTable
import dev.ayer.kinemagraphein.entity.media.MediaBaseData

private data class MediaBaseDataImpl(
    override val id: String,
    override val isFavorite: Boolean,
    override val name: String,
    override val mediumImageUrl: String?,
    override val originalImageUrl: String?,
    override val summary: String?
) : MediaBaseData

fun FavoritesTable.toMediaBaseData(isFavorite: Boolean): MediaBaseData = MediaBaseDataImpl(
    id = id,
    name = name,
    isFavorite = isFavorite,
    mediumImageUrl = this.mediumImageUrl,
    originalImageUrl = this.originalImageUrl,
    summary = this.summary
)

fun RecentTable.toMediaBaseData(isFavorite: Boolean) : MediaBaseData = MediaBaseDataImpl(
    id = id,
    name = name,
    isFavorite = isFavorite,
    mediumImageUrl = this.mediumImageUrl,
    originalImageUrl = this.originalImageUrl,
    summary = this.summary
)

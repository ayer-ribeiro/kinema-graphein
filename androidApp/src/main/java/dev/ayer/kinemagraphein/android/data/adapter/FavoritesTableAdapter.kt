package dev.ayer.kinemagraphein.android.data.adapter

import dev.ayer.kinemagraphein.android.data.sources.room.entity.FavoritesTable
import dev.ayer.kinemagraphein.android.entity.media.MediaBaseData

fun MediaBaseData.toFavoritesTable(): FavoritesTable = FavoritesTable(
    id = id,
    name = name,
    mediumImageUrl = mediumImageUrl,
    originalImageUrl = originalImageUrl,
    summary = summary
)

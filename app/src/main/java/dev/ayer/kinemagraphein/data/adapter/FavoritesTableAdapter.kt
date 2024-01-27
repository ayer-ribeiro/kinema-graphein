package dev.ayer.kinemagraphein.data.adapter

import dev.ayer.kinemagraphein.data.sources.room.entity.FavoritesTable
import dev.ayer.kinemagraphein.entity.media.MediaBaseData

fun MediaBaseData.toFavoritesTable(): FavoritesTable = FavoritesTable(
    id = id,
    name = name,
    mediumImageUrl = mediumImageUrl,
    originalImageUrl = originalImageUrl,
    summary = summary
)
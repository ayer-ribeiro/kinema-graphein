package dev.ayer.kinemagraphein.data.adapter

import dev.ayer.kinemagraphein.data.sources.room.entity.RecentTable
import dev.ayer.kinemagraphein.entity.media.MediaBaseData

fun MediaBaseData.toRecentTable(lastAccess: Long): RecentTable = RecentTable(
    id = id,
    name = name,
    mediumImageUrl = mediumImageUrl,
    originalImageUrl = originalImageUrl,
    summary = summary,
    lastAccessed = lastAccess
)
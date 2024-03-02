package dev.ayer.kinemagraphein.android.data.adapter

import dev.ayer.kinemagraphein.android.data.sources.room.entity.RecentTable
import dev.ayer.kinemagraphein.entity.media.MediaBaseData

fun MediaBaseData.toRecentTable(lastAccess: Long): RecentTable = RecentTable(
    id = id,
    name = name,
    mediumImageUrl = mediumImageUrl,
    originalImageUrl = originalImageUrl,
    summary = summary,
    lastAccessed = lastAccess
)

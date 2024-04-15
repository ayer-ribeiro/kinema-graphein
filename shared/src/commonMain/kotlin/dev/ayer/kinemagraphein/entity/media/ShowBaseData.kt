package dev.ayer.kinemagraphein.entity.media

import dev.ayer.kinemagraphein.entity.time.Schedule
import kotlinx.datetime.Instant

interface ShowBaseData: Coverable, SearchResult {
    override val id: Long
    override val isFavorite: Boolean
    override val name: String
    override val mediumImageUrl: String?
    override val originalImageUrl: String?
    override val summary: String?
    override val releaseDate: String?
    val genres: List<String>
    val schedule: Schedule
    val lastAccess: Instant?
    val lastModified: Instant?
}

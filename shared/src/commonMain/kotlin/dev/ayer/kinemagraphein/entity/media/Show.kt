package dev.ayer.kinemagraphein.entity.media

import dev.ayer.kinemagraphein.entity.time.Schedule
import kotlinx.datetime.Instant

data class Show(
    override val id: Long,
    override val isFavorite: Boolean,
    override val summary: String? = null,
    override val releaseDate: String? = null,
    override val name: String,
    override val mediumImageUrl: String?,
    override val originalImageUrl: String?,
    override val genres: List<String>,
    override val schedule: Schedule,
    override val lastAccess: Instant?,
    override val lastModified: Instant?,
    override val seasons: List<Season>,
): ShowBaseData, Coverable, SearchResult, MediaInfo, Favoritable
package dev.ayer.kinemagraphein.entity.media

import dev.ayer.kinemagraphein.entity.time.Schedule

data class Show(
    override val id: String,
    override val isFavorite: Boolean,
    override val summary: String?,
    override val releaseDate: String,
    override val name: String,
    override val mediumImageUrl: String?,
    override val originalImageUrl: String?,
    override val genres: List<String>,
    val seasons: List<Season>,
    val schedule: Schedule,
): Media

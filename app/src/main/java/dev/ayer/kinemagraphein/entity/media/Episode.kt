package dev.ayer.kinemagraphein.entity.media

data class Episode(
    override val id: String,
    override val name: String,
    override val isFavorite: Boolean,
    override val mediumImageUrl: String?,
    override val originalImageUrl: String?,
    override val summary: String?,
    val number: Int,
    val season: Int,
    val releaseDate: String?,
): MediaBaseData

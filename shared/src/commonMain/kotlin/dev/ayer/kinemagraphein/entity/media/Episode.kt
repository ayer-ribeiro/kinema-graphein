package dev.ayer.kinemagraphein.entity.media

data class Episode(
    val id: String,
    override val name: String,
    override val isFavorite: Boolean,
    val summary: String?,
    val number: Int,
    val season: Int,
    val releaseDate: String?,
    override val mediumImageUrl: String?,
    override val originalImageUrl: String?,
): Coverable, Favoritable

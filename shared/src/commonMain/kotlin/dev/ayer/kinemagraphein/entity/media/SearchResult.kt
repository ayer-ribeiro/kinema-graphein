package dev.ayer.kinemagraphein.entity.media

interface SearchResult: Coverable, Favoritable {
    val id: Long
    val summary: String?
    val releaseDate: String?
    override val isFavorite: Boolean
    override val name: String
    override val mediumImageUrl: String?
    override val originalImageUrl: String?
}
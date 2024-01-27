package dev.ayer.kinemagraphein.entity.media

interface MediaBaseData {
    val id: String
    val isFavorite: Boolean
    val name: String
    val mediumImageUrl: String?
    val originalImageUrl: String?
    val summary: String?
}

package dev.ayer.kinemagraphein.entity.media

sealed interface Media: MediaBaseData {
    val releaseDate: String
    val genres: List<String>
}

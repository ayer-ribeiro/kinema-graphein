package dev.ayer.kinemagraphein.android.entity.media

sealed interface Media: MediaBaseData {
    val releaseDate: String
    val genres: List<String>
}

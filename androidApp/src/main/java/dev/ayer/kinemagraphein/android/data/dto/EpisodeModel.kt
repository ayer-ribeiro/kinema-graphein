package dev.ayer.kinemagraphein.android.data.dto

import retrofit2.http.Field

data class EpisodeModel(
    val id: String,
    val name: String,
    @Field("airdate") val airDate: String?,
    val image: ImageDto?,
    val summary: String?,
    val season: Int,
    val number: Int
)

package dev.ayer.kinemagraphein.data.dto

import de.jensklingenberg.ktorfit.http.Field
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeModel(
    val id: String,
    val name: String,
    @Field("airdate") val airDate: String? = null,
    val image: ImageDto? = null,
    val summary: String? = null,
    val season: Int,
    val number: Int
)

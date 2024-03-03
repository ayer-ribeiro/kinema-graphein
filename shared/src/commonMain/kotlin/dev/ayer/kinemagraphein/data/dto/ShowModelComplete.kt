package dev.ayer.kinemagraphein.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowModelComplete(
    val id: Int,
    val name: String,
    val genres: List<String>,
    val schedule: ScheduleModel,
    val image: ImageDto?,
    val summary: String,
    val premiered: String,
    @SerialName("_embedded") val embedded: SeasonsEmbedModel? = null
)

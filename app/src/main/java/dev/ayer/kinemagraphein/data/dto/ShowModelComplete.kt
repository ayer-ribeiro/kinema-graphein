package dev.ayer.kinemagraphein.data.dto

import com.squareup.moshi.Json

data class ShowModelComplete(
    val id: Int,
    val name: String,
    val genres: List<String>,
    val schedule: ScheduleModel,
    val image: ImageDto?,
    val summary: String,
    val premiered: String,
    val _embedded: SeasonsEmbedModel?
)
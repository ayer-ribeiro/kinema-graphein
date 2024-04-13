package dev.ayer.kinemagraphein.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ShowModelBase(
    val id: Long,
    val name: String,
    val genres: List<String>,
    val schedule: ScheduleModel,
    val image: ImageDto? = null,
    val summary: String,
    val premiered: String? = null,
)

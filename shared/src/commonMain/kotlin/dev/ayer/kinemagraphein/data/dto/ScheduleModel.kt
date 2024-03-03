package dev.ayer.kinemagraphein.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleModel(
    val time: String,
    val days: List<String>
)

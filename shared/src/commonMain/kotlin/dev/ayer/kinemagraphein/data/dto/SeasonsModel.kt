package dev.ayer.kinemagraphein.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SeasonsModel(
    val id: String,
    val summary: String? = null,
    val name: String,
    val mediumImageUrl: String? = null,
    val originalImageUrl: String? = null,
    val number: Int,
)

package dev.ayer.kinemagraphein.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SearchResult(
    val score: Double,
    val show: ShowModelBase
)

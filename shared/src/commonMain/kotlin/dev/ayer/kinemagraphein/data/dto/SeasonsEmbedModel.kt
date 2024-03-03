package dev.ayer.kinemagraphein.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SeasonsEmbedModel(
    val episodes: List<EpisodeModel>? = null,
    val seasons: List<SeasonsModel>? = null
)

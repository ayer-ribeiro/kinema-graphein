package dev.ayer.kinemagraphein.data.adapter

import dev.ayer.kinemagraphein.data.dto.EpisodeModel
import dev.ayer.kinemagraphein.entity.media.Episode

fun EpisodeModel.toEpisode(): Episode = Episode(
    id = id,
    name = name,
    releaseDate = airDate,
    originalImageUrl = image?.original,
    mediumImageUrl = image?.medium,
    summary = summary,
    season = season,
    number = number,
    isFavorite = false
)

fun List<EpisodeModel>.toEpisodeList(): List<Episode> {
    return map { it.toEpisode() }
}
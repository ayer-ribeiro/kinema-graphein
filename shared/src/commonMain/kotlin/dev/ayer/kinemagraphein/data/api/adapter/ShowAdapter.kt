package dev.ayer.kinemagraphein.data.api.adapter

import dev.ayer.kinemagraphein.data.dto.SeasonsEmbedModel
import dev.ayer.kinemagraphein.data.dto.ShowModelBase
import dev.ayer.kinemagraphein.data.dto.ShowModelComplete
import dev.ayer.kinemagraphein.entity.media.Season
import dev.ayer.kinemagraphein.entity.media.Show
import kotlinx.datetime.Instant

fun SeasonsEmbedModel.toSeasonList(): List<Season> {
    return seasons!!.map { season ->
        Season(
            summary = season.summary,
            id = season.id,
            isFavorite = false,
            name = season.name,
            mediumImageUrl = season.mediumImageUrl,
            originalImageUrl = season.originalImageUrl,
            number = season.number,
            episodes = this.episodes!!.filter { it.season == season.number }.toEpisodeList()
        )
    }
}

fun ShowModelComplete.toShow(
    lastAccessed: Instant?,
    isFavorite: Boolean
) = Show(
    id = id,
    name = name,
    isFavorite = isFavorite,
    mediumImageUrl = image?.medium,
    originalImageUrl = image?.original,
    summary = summary,
    genres = genres,
    seasons = embedded?.toSeasonList() ?: emptyList(),
    schedule = schedule.toSchedule(),
    releaseDate = premiered,
    lastAccess = lastAccessed,
)

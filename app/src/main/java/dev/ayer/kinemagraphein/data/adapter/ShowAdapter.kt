package dev.ayer.kinemagraphein.data.adapter

import dev.ayer.kinemagraphein.data.dto.SeasonsEmbedModel
import dev.ayer.kinemagraphein.data.dto.ShowModelBase
import dev.ayer.kinemagraphein.data.dto.ShowModelComplete
import dev.ayer.kinemagraphein.entity.media.Season
import dev.ayer.kinemagraphein.entity.media.Show

fun ShowModelBase.toShow() = Show(
    id = id.toString(),
    name = name,
    isFavorite = false,
    mediumImageUrl = image?.medium,
    originalImageUrl = image?.original,
    summary = summary,
    genres = genres,
    seasons = emptyList(),
    schedule = schedule.toSchedule(),
    releaseDate = premiered

)


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

fun ShowModelComplete.toShow() = Show(
    id = id.toString(),
    name = name,
    isFavorite = false,
    mediumImageUrl = image?.medium,
    originalImageUrl = image?.original,
    summary = summary,
    genres = genres,
    seasons = _embedded?.toSeasonList() ?: emptyList(),
    schedule = schedule.toSchedule(),
    releaseDate = premiered
)

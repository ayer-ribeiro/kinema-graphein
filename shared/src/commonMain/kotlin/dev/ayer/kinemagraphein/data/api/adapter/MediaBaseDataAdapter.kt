package dev.ayer.kinemagraphein.data.api.adapter

import dev.ayer.kinemagraphein.data.database.Shows
import dev.ayer.kinemagraphein.data.dto.ShowModelBase
import dev.ayer.kinemagraphein.entity.media.ShowBase
import dev.ayer.kinemagraphein.entity.media.ShowBaseData
import dev.ayer.kinemagraphein.entity.time.Schedule
import kotlinx.datetime.Instant

fun Shows.toMediaBaseData(): ShowBase = ShowBase(
    id = id,
    name = name,
    isFavorite = is_favorite,
    mediumImageUrl = medium_image_url,
    originalImageUrl = original_image_url,
    summary = summary,
    releaseDate = release_date,
    genres = genres.orEmpty(),
    schedule = Schedule(
        time = schedule_time,
        weekdays = schedule_days,
    ),
    lastAccess = last_access,
)
fun List<Shows>.toMediaBaseData(): List<ShowBase> = this.map { it.toMediaBaseData() }

fun ShowModelBase.toMediaBaseData(
    isFavorite: Boolean,
    lastAccess: Instant?
) = ShowBase(
    id = id.toString(),
    name = name,
    isFavorite = isFavorite,
    mediumImageUrl = image?.medium,
    originalImageUrl = image?.original,
    summary = summary,
    releaseDate = premiered,
    genres = genres,
    schedule = Schedule(
        time = schedule.time,
        weekdays = schedule.days,
    ),
    lastAccess = lastAccess,
)

fun ShowBaseData.withNewFavoriteState(isFavorite: Boolean) = ShowBase(
    id = id,
    name = name,
    isFavorite = isFavorite,
    mediumImageUrl = mediumImageUrl,
    originalImageUrl = originalImageUrl,
    summary = summary,
    releaseDate = releaseDate,
    genres = genres,
    schedule = schedule,
    lastAccess = lastAccess,
)
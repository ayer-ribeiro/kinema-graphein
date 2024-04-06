package dev.ayer.kinemagraphein.entity.media

fun Show.asShowBase(): ShowBase {
    return ShowBase(
        id = this.id,
        isFavorite = this.isFavorite,
        summary = this.summary,
        releaseDate = this.releaseDate,
        name = this.name,
        mediumImageUrl = this.mediumImageUrl,
        originalImageUrl = this.originalImageUrl,
        genres = this.genres,
        schedule = this.schedule,
        lastAccess = this.lastAccess
    )
}
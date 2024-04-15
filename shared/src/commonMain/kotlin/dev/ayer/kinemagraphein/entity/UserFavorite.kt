package dev.ayer.kinemagraphein.entity

import dev.ayer.kinemagraphein.entity.media.ShowBase

data class UserFavorite(
    val items: List<ShowBase>,
    val containsAllFavorites: Boolean
)
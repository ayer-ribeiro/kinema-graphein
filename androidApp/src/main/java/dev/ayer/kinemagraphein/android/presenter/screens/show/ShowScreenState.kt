package dev.ayer.kinemagraphein.android.presenter.screens.show

import kotlinx.collections.immutable.ImmutableList

data class ShowScreenState(
    val isLoading: Boolean,
    val isError: Boolean,
    val showScreenData: ShowScreenData?,
    val seasons: ImmutableList<ShowScreenSeasonData>,
    val currentSeason: ShowScreenSeasonData?
)

data class ShowScreenData(
    val id: String,
    val isFavorite: Boolean,
    val summary: String? = null,
    val releaseDate: String? = null,
    val name: String,
    val imageUrl: String?,
    val genres: ImmutableList<String>,
    val timeSchedule: String,
    val weekdays: ImmutableList<String>,
)

data class ShowScreenSeasonData(
    val number: Int,
    val episodes: ImmutableList<ShowScreenEpisodeData>,
)

data class ShowScreenEpisodeData(
    val name: String,
    val summary: String?,
    val releaseDate: String?,
    val number: Int,
    val season: Int,
    val imageUrl: String?,
)
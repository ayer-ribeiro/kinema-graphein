package dev.ayer.kinemagraphein.android.presenter.navigation.destination

import dev.ayer.kinemagraphein.android.presenter.navigation.arguments.Argument
import dev.ayer.kinemagraphein.android.presenter.navigation.arguments.Argument.Companion.with
import dev.ayer.kinemagraphein.android.presenter.navigation.params.AppNavParam

sealed class Destination(
    internal val appNavDestination: AppNavDestinations,
    val arguments: List<Argument>
) {
    data object Home : Destination(
        appNavDestination = AppNavDestinations.Home,
        arguments = emptyList(),
    )

    class Show(showId: Long) : Destination(
        appNavDestination = AppNavDestinations.Show,
        arguments = listOf(
            AppNavParam.ShowId.with(value = showId)
        ),
    )

    data object FavoriteList : Destination(
        appNavDestination = AppNavDestinations.FavoriteList,
        arguments = emptyList(),
    )

    class Episode(
        showId: Long,
        seasonNumber: Int,
        episodeNumber: Int
    ) : Destination(
        appNavDestination = AppNavDestinations.Episode,
        arguments = listOf(
            AppNavParam.ShowId.with(value = showId),
            AppNavParam.SeasonNumber.with(value = seasonNumber),
            AppNavParam.EpisodeNumber.with(value = episodeNumber)
        )
    )
}
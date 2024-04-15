package dev.ayer.kinemagraphein.android.presenter.navigation.destination

import androidx.navigation.NamedNavArgument
import dev.ayer.kinemagraphein.android.presenter.navigation.params.AppNavParam
import dev.ayer.kinemagraphein.android.presenter.navigation.params.asNamedNavArgumentParam

internal enum class AppNavDestinations(
    val baseRoute: String,
    val params: List<AppNavParam>
) {
    Home(
        baseRoute = "home",
        params = emptyList()
    ),
    Show(
        baseRoute = "show",
        params = listOf(
            AppNavParam.ShowId
        )
    ),
    FavoriteList(
        baseRoute = "favorites",
        params = emptyList()
    ),
    Episode(
        baseRoute = "episode",
        params = listOf(
            AppNavParam.ShowId,
            AppNavParam.SeasonNumber,
            AppNavParam.EpisodeNumber,
        )
    )
}

internal val AppNavDestinations.namedNavArgumentsParams: List<NamedNavArgument>
    get() = this.params.map { it.asNamedNavArgumentParam() }

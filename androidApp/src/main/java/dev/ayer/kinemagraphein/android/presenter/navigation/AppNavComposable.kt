package dev.ayer.kinemagraphein.android.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import dev.ayer.kinemagraphein.android.presenter.navigation.arguments.appArguments
import dev.ayer.kinemagraphein.android.presenter.navigation.destination.AppNavDestinations
import dev.ayer.kinemagraphein.android.presenter.screens.episode.EpisodeScreen
import dev.ayer.kinemagraphein.android.presenter.screens.home.HomeScreen
import dev.ayer.kinemagraphein.android.presenter.screens.show.ShowScreen

@Composable
internal fun AppNavDestinations.AppNavComposable(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
) {
    when (this) {
        AppNavDestinations.Home -> {
            HomeScreen(navController)
        }
        AppNavDestinations.Show -> {
            val showId = backStackEntry.appArguments.getShowId()
            ShowScreen(navController, showId)
        }

        AppNavDestinations.FavoriteList -> {}
        AppNavDestinations.Episode -> {
            val showId = backStackEntry.appArguments.getShowId()
            val season = backStackEntry.appArguments.getSeasonNumber()
            val number = backStackEntry.appArguments.getEpisodeNumber()
            EpisodeScreen(navController, showId, season, number)
        }
    }
}

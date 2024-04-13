package dev.ayer.kinemagraphein.android.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.ayer.kinemagraphein.android.presenter.screens.episode.EpisodeScreen
import dev.ayer.kinemagraphein.android.presenter.screens.home.HomeScreen
import dev.ayer.kinemagraphein.android.presenter.screens.show.ShowScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(route = Home.route) {
            HomeScreen(navController)
        }
        composable(
            route = "${Show.route}/{showId}",
            arguments = listOf(
                navArgument("showId") { type = NavType.LongType},
            )
        ) { backStackEntry ->
            val showId = backStackEntry.arguments!!.getLong("showId")
            ShowScreen(navController, showId)
        }
        composable(
            route = "${Episode.route}/{showId}/{season}/{number}",
            arguments = listOf(
                navArgument("showId"){ type = NavType.LongType },
                navArgument("season"){ type = NavType.IntType },
                navArgument("number"){ type = NavType.IntType },
            )
        ) { backStackEntry ->
            val showId = backStackEntry.arguments!!.getLong("showId")
            val season = backStackEntry.arguments!!.getInt("season")
            val number = backStackEntry.arguments!!.getInt("number")
            EpisodeScreen(navController, showId, season, number)
        }
    }
}

fun NavHostController.navigateSingleTopToHome() = navigateSingleTopTo(Home.route)
fun NavHostController.navigateSingleTopEpisode(showId: Long, season: Int, number: Int) {
    navigateSingleTopTo("${Episode.route}/$showId/$season/$number")
}
fun NavHostController.navigateSingleTopToShow(id: Long) {
    navigateSingleTopTo("${Show.route}/$id")
}

private fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
    }

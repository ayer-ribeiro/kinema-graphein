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
import dev.ayer.kinemagraphein.android.presenter.screens.series.ShowScreen

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
            route = "${Series.route}/{seriesId}",
            arguments = listOf(
                navArgument("seriesId") { type = NavType.StringType},
            )
        ) { backStackEntry ->
            val seriesId = backStackEntry.arguments!!.getString("seriesId")!!
            ShowScreen(navController, seriesId)
        }
        composable(
            route = "${Episode.route}/{seriesId}/{season}/{number}",
            arguments = listOf(
                navArgument("seriesId"){ type = NavType.StringType },
                navArgument("season"){ type = NavType.IntType },
                navArgument("number"){ type = NavType.IntType },
            )
        ) { backStackEntry ->
            val seriesId = backStackEntry.arguments!!.getString("seriesId")!!
            val season = backStackEntry.arguments!!.getInt("season")
            val number = backStackEntry.arguments!!.getInt("number")
            EpisodeScreen(navController, seriesId, season, number)
        }
    }
}

fun NavHostController.navigateSingleTopToHome() = navigateSingleTopTo(Home.route)
fun NavHostController.navigateSingleTopEpisode(seriesId: String, season: Int, number: Int) {
    navigateSingleTopTo("${Episode.route}/$seriesId/$season/$number")
}
fun NavHostController.navigateSingleTopToSeries(id: String) {
    navigateSingleTopTo("${Series.route}/$id")
}

private fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
    }

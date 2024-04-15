package dev.ayer.kinemagraphein.android.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.ayer.kinemagraphein.android.presenter.navigation.destination.AppNavDestinations
import dev.ayer.kinemagraphein.android.presenter.navigation.destination.Destination
import dev.ayer.kinemagraphein.android.presenter.navigation.destination.genericRoute
import dev.ayer.kinemagraphein.android.presenter.navigation.destination.namedNavArgumentsParams
import dev.ayer.kinemagraphein.android.presenter.navigation.destination.route

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
        modifier = modifier
    ) {
        AppNavDestinations.entries.forEach { appNavDestination ->
            composable(
                route = appNavDestination.genericRoute,
                arguments = appNavDestination.namedNavArgumentsParams
            ) { backStackEntry ->
                appNavDestination.AppNavComposable(
                    navController = navController,
                    backStackEntry = backStackEntry
                )
            }
        }
    }
}

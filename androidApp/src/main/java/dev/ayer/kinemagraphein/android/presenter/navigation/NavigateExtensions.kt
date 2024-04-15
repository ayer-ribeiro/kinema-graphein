package dev.ayer.kinemagraphein.android.presenter.navigation

import androidx.navigation.NavHostController
import dev.ayer.kinemagraphein.android.presenter.navigation.destination.Destination
import dev.ayer.kinemagraphein.android.presenter.navigation.destination.NavHostControllerDestination
import dev.ayer.kinemagraphein.android.presenter.navigation.destination.route

fun NavHostControllerDestination.navigate() {
    this.navController
        .navigateTo(destination = this.destination)
}

fun Destination.navigateWith(navController: NavHostController) {
    navController.navigateTo(this)
}

fun NavHostController.navigateTo(destination: Destination) {
    this.navigate(destination.route) {
        restoreState = true
    }
}
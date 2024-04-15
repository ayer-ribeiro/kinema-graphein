package dev.ayer.kinemagraphein.android.presenter.navigation.destination

import androidx.navigation.NavHostController

class NavHostControllerDestination(
    val navController: NavHostController,
    val destination: Destination
)

fun Destination.with(navController: NavHostController) = NavHostControllerDestination(
    navController = navController,
    destination = this
)

package dev.ayer.kinemagraphein.android.presenter.navigation

interface Destination {
    val route: String
}

object Home : Destination {
    override val route = "home"
}

object Show : Destination {
    override val route = "show"
}

object Episode : Destination {
    override val route = "episode"
}

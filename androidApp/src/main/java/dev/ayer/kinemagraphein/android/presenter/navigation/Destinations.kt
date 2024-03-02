package dev.ayer.kinemagraphein.android.presenter.navigation

interface Destination {
    val route: String
}

object Home : Destination {
    override val route = "home"
}

object Series : Destination {
    override val route = "series"
}

object Episode : Destination {
    override val route = "episode"
}

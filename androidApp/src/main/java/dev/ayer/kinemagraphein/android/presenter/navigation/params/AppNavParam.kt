package dev.ayer.kinemagraphein.android.presenter.navigation.params

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

enum class AppNavParam(
    val paramName: String,
    val paramType: NavType<*>
) {
    ShowId(
        paramName = "showId",
        paramType = NavType.LongType
    ),
    SeasonNumber(
        paramName = "season",
        paramType = NavType.IntType
    ),
    EpisodeNumber(
        paramName = "number",
        paramType = NavType.IntType
    )
}

internal fun AppNavParam.asNamedNavArgumentParam(): NamedNavArgument {
    return navArgument(this.paramName) {
        type = this@asNamedNavArgumentParam.paramType
    }
}
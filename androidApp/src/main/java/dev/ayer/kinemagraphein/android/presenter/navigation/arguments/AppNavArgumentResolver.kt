package dev.ayer.kinemagraphein.android.presenter.navigation.arguments

import androidx.navigation.NavBackStackEntry
import dev.ayer.kinemagraphein.android.presenter.navigation.params.AppNavParam

class AppNavArgumentResolver(
    private val backStackEntry: NavBackStackEntry
) {
    fun getShowId(): Long = backStackEntry.arguments!!.getLong(AppNavParam.ShowId.paramName)
    fun getSeasonNumber(): Int = backStackEntry.arguments!!.getInt(AppNavParam.SeasonNumber.paramName)
    fun getEpisodeNumber(): Int = backStackEntry.arguments!!.getInt(AppNavParam.EpisodeNumber.paramName)
}

val NavBackStackEntry.appArguments get() = AppNavArgumentResolver(this)

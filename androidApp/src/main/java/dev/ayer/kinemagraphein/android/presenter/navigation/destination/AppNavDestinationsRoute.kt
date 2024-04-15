package dev.ayer.kinemagraphein.android.presenter.navigation.destination

import dev.ayer.kinemagraphein.utils.string.Braces
import dev.ayer.kinemagraphein.utils.string.joinToString

internal val AppNavDestinations.genericRoute: String
    get() {
        if (params.isEmpty()) {
            return this.baseRoute
        }
        val params = params
            .map { it.paramName }
            .joinToString(prefix = "/", separator = "/", wrapper = Braces)

        return this.baseRoute + params
    }


val Destination.route: String
    get() {
        val baseRoute = this.appNavDestination.baseRoute

        if (this.arguments.isEmpty()) {
            return baseRoute
        }

        val argumentValues = this.arguments
            .map { it.value }
            .joinToString(prefix = "/", separator = "/")

        return baseRoute + argumentValues
    }
package dev.ayer.kinemagraphein.android.presenter.navigation.arguments

import dev.ayer.kinemagraphein.android.presenter.navigation.params.AppNavParam

class Argument private constructor(
    val appNavParam: AppNavParam,
    val value: String,
) {
    companion object {
        fun AppNavParam.with(value: Any): Argument {
            return Argument(
                appNavParam = this,
                value = value.toString()
            )
        }
    }
}
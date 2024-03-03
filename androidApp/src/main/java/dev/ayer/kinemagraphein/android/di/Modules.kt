package dev.ayer.kinemagraphein.android.di

import dev.ayer.kinemagraphein.di.ktorfitModule
import dev.ayer.kinemagraphein.di.useCaseModules

val appModules = listOf(
    viewModelModules,
    useCaseModules,
    repositoriesModule,
    ktorfitModule,
    roomModule
)

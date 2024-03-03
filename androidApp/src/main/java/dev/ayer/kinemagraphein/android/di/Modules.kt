package dev.ayer.kinemagraphein.android.di

import dev.ayer.kinemagraphein.di.retrofitModule
import dev.ayer.kinemagraphein.di.useCaseModules

val appModules = listOf(
    viewModelModules,
    useCaseModules,
    repositoriesModule,
    retrofitModule,
    roomModule
)

package dev.ayer.kinemagraphein.data.database.di

import dev.ayer.kinemagraphein.data.database.AndroidDriverFactoryImpl
import dev.ayer.kinemagraphein.data.database.setup.DriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sqlDelightDriverModule: Module = module {
    single<DriverFactory> {
        AndroidDriverFactoryImpl(
            get(),
        )
    }
}
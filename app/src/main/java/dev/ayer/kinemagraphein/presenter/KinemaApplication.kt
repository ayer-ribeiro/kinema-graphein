package dev.ayer.kinemagraphein.presenter

import android.app.Application
import dev.ayer.kinemagraphein.di.repositoriesModule
import dev.ayer.kinemagraphein.di.retrofitModule
import dev.ayer.kinemagraphein.di.roomModule
import dev.ayer.kinemagraphein.di.useCaseModules
import dev.ayer.kinemagraphein.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KinemaApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KinemaApplication)
            this.printLogger(Level.DEBUG)
            modules(
                viewModelModules,
                useCaseModules,
                repositoriesModule,
                retrofitModule,
                roomModule
            )
        }
    }
}
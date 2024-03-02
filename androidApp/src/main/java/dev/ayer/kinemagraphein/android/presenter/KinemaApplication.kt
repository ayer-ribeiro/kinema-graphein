package dev.ayer.kinemagraphein.android.presenter

import android.app.Application
import dev.ayer.kinemagraphein.android.di.repositoriesModule
import dev.ayer.kinemagraphein.android.di.retrofitModule
import dev.ayer.kinemagraphein.android.di.roomModule
import dev.ayer.kinemagraphein.android.di.useCaseModules
import dev.ayer.kinemagraphein.android.di.viewModelModules
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

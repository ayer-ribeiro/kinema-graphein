package dev.ayer.kinemagraphein.android.presenter

import android.app.Application
import dev.ayer.kinemagraphein.android.di.presenterModule
import dev.ayer.kinemagraphein.di.startKmpKoin
import org.koin.android.ext.koin.androidContext

class KinemaApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKmpKoin(presenterModule = presenterModule) {
            this.androidContext(this@KinemaApplication)
        }
    }
}

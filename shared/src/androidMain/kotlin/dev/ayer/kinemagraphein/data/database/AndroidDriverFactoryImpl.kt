package dev.ayer.kinemagraphein.data.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dev.ayer.kinemagraphein.data.database.setup.DriverFactory

internal class AndroidDriverFactoryImpl(
    private val context: Context
) : DriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            KinemaDatabase.Schema,
            context,
            "kimena_graphein_database.db"
        )
    }
}
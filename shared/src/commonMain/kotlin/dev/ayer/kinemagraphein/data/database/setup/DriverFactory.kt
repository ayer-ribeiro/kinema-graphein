package dev.ayer.kinemagraphein.data.database.setup

import app.cash.sqldelight.db.SqlDriver

interface DriverFactory {
    fun createDriver(): SqlDriver
}
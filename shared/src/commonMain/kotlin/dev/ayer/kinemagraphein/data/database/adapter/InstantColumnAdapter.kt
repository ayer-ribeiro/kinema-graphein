package dev.ayer.kinemagraphein.data.database.adapter

import app.cash.sqldelight.ColumnAdapter
import kotlinx.datetime.Instant

class InstantColumnAdapter: ColumnAdapter<Instant, Long> {
    override fun decode(databaseValue: Long): Instant {
        return Instant.fromEpochMilliseconds(databaseValue)
    }

    override fun encode(value: Instant): Long {
        return value.toEpochMilliseconds()
    }
}
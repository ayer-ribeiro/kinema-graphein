package dev.ayer.kinemagraphein.data.database.adapter

import app.cash.sqldelight.ColumnAdapter

class StringListColumnAdapter: ColumnAdapter<List<String>, String> {
    override fun decode(databaseValue: String): List<String> {
        return databaseValue.split(",")
    }

    override fun encode(value: List<String>): String {
        return value.joinToString(separator = ",")
    }
}
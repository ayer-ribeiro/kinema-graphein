package dev.ayer.kinemagraphein.data.config

import kotlinx.serialization.json.Json

fun jsonConfig() = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
}

package dev.ayer.kinemagraphein.data.config

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

private val logLevel = LogLevel.HEADERS

fun Logging.Config.loggingConfig(logger: Logger) {
    Napier.base(DebugAntilog())
    this.logger = logger
    this.level = logLevel
}

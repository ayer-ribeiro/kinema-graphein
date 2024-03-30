package dev.ayer.kinemagraphein.utils.logging

import io.github.aakira.napier.Napier
import io.ktor.client.plugins.logging.Logger

internal class KtorLoggerImpl: Logger {
    override fun log(message: String) {
        Napier.d(
            message = message,
            throwable = null,
            tag = "HTTP Client",
        )
    }
}

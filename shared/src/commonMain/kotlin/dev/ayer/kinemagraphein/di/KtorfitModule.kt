package dev.ayer.kinemagraphein.di

import de.jensklingenberg.ktorfit.Ktorfit
import dev.ayer.kinemagraphein.data.config.jsonConfig
import dev.ayer.kinemagraphein.data.config.loggingConfig
import dev.ayer.kinemagraphein.data.api.KtorfitApiService
import dev.ayer.kinemagraphein.utils.logging.KtorLoggerImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

private const val apiBaseUrl = "https://api.tvmaze.com/"

val ktorfitModule = module {
    single<Logger> {
        KtorLoggerImpl()
    }

    single<Json> {
        jsonConfig()
    }

    single<HttpClient> {
        HttpClient(CIO) {
            install(Logging) {
                loggingConfig(get())
            }

            install(ContentNegotiation) {
                json(get())
            }
        }
    }

    single<Ktorfit> {
        val client: HttpClient = get()
        Ktorfit.Builder()
            .baseUrl(apiBaseUrl)
            .httpClient(client)
            .build()
    }

    single<KtorfitApiService> {
        val ktorfit: Ktorfit = get()
        ktorfit.create()
    }
}

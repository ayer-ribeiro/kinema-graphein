package dev.ayer.kinemagraphein.data.database.di

import app.cash.sqldelight.db.SqlDriver
import dev.ayer.kinemagraphein.data.database.KinemaDatabase
import dev.ayer.kinemagraphein.data.database.Shows
import dev.ayer.kinemagraphein.data.database.adapter.InstantColumnAdapter
import dev.ayer.kinemagraphein.data.database.adapter.StringListColumnAdapter
import dev.ayer.kinemagraphein.data.database.setup.DriverFactory
import org.koin.dsl.module

internal val sqlDelightModule = module {
    factory<StringListColumnAdapter> {
        StringListColumnAdapter()
    }

    factory<InstantColumnAdapter> {
        InstantColumnAdapter()
    }

    factory<Shows.Adapter> {
        val instantLongAdapter: InstantColumnAdapter = get()
        val stringListAdapter: StringListColumnAdapter = get()

        Shows.Adapter(
            genresAdapter = stringListAdapter,
            schedule_daysAdapter = stringListAdapter,
            last_accessAdapter = instantLongAdapter,
        )
    }

    single<SqlDriver> {
        val factory: DriverFactory = get()
        factory.createDriver()
    }

    single<KinemaDatabase> {
        KinemaDatabase(
            driver = get(),
            showsAdapter = get()
        )
    }
}
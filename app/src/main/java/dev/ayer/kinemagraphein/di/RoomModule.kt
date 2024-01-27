package dev.ayer.kinemagraphein.di

import androidx.room.Room
import dev.ayer.kinemagraphein.data.sources.room.AppRoomDatabase
import org.koin.dsl.module

private const val databaseName = "currency-conversions-db"

val roomModule = module {
    single<AppRoomDatabase> {
        Room.databaseBuilder(
            get(),
            AppRoomDatabase::class.java, databaseName
        ).build()
    }
}

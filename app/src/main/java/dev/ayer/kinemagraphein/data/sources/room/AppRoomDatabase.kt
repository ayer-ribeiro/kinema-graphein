package dev.ayer.kinemagraphein.data.sources.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.ayer.kinemagraphein.data.sources.room.dao.FavoriteDao
import dev.ayer.kinemagraphein.data.sources.room.dao.RecentDao
import dev.ayer.kinemagraphein.data.sources.room.entity.FavoritesTable
import dev.ayer.kinemagraphein.data.sources.room.entity.RecentTable

@Database(
    entities = [
        FavoritesTable::class,
        RecentTable::class
    ],
    version = 1,
)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun recentDao(): RecentDao
}

package dev.ayer.kinemagraphein.data.sources.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.ayer.kinemagraphein.data.sources.room.entity.FavoritesTable
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    fun getAll(): Flow<List<FavoritesTable>>

    @Query("SELECT COUNT(*) FROM favorites WHERE id = :id")
    suspend fun existInFavorites(id: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoritesTable: FavoritesTable)

    @Delete
    suspend fun delete(favoritesTable: FavoritesTable)

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun deleteById(id: String)
}
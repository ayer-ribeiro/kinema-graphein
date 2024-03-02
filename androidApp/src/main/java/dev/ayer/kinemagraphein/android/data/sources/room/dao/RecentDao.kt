package dev.ayer.kinemagraphein.android.data.sources.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import dev.ayer.kinemagraphein.android.data.sources.room.entity.RecentTable
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentDao {
    @Query("SELECT COUNT(*) FROM recent")
    suspend fun getRowCount(): Int

    @Query("SELECT * FROM recent ORDER BY lastAccessed DESC")
    fun getAll(): Flow<List<RecentTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recentTable: RecentTable)

    @Delete
    suspend fun delete(recentTable: RecentTable)

    @Query("DELETE FROM recent WHERE lastAccessed = (SELECT MIN(lastAccessed) FROM recent)")
    suspend fun deleteOldest()

    @Query("DELETE FROM recent WHERE id = :id")
    suspend fun deleteById(id: String)

    @Transaction
    suspend fun insertWithReplace(recentTable: RecentTable) {
        insert(recentTable)

        val currentSize = getRowCount()
        val maxSize = 10

        if (currentSize > maxSize) {
            deleteOldest()
        }
    }
}

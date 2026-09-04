package com.paawan.panchang.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paawan.panchang.data.local.db.entity.FestivalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FestivalDao {
    @Query("SELECT * FROM festival_table ORDER BY dateIso ASC")
    fun getAllFestivals(): Flow<List<FestivalEntity>>

    @Query("SELECT * FROM festival_table WHERE id = :id LIMIT 1")
    fun getFestivalById(id: String): Flow<FestivalEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFestivals(festivals: List<FestivalEntity>)
}

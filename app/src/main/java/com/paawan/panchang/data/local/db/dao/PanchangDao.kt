package com.paawan.panchang.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paawan.panchang.data.local.db.entity.PanchangEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PanchangDao {
    @Query("SELECT * FROM panchang_table WHERE dateIso = :dateIso LIMIT 1")
    fun getPanchangForDate(dateIso: String): Flow<PanchangEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPanchang(entity: PanchangEntity)
}

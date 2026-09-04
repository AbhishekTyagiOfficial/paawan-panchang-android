package com.paawan.panchang.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paawan.panchang.data.local.db.dao.FestivalDao
import com.paawan.panchang.data.local.db.dao.PanchangDao
import com.paawan.panchang.data.local.db.entity.FestivalEntity
import com.paawan.panchang.data.local.db.entity.PanchangEntity

@Database(
    entities = [PanchangEntity::class, FestivalEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun panchangDao(): PanchangDao
    abstract fun festivalDao(): FestivalDao
}

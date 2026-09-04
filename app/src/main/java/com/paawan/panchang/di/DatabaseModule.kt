package com.paawan.panchang.di

import android.content.Context
import androidx.room.Room
import com.paawan.panchang.data.local.db.AppDatabase
import com.paawan.panchang.data.local.db.dao.FestivalDao
import com.paawan.panchang.data.local.db.dao.PanchangDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "paawan_panchang_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providePanchangDao(database: AppDatabase): PanchangDao {
        return database.panchangDao()
    }

    @Provides
    fun provideFestivalDao(database: AppDatabase): FestivalDao {
        return database.festivalDao()
    }
}

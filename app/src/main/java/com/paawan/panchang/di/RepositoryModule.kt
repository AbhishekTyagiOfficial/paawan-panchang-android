package com.paawan.panchang.di

import com.paawan.panchang.data.repository.FestivalRepositoryImpl
import com.paawan.panchang.data.repository.PanchangRepositoryImpl
import com.paawan.panchang.data.repository.VratRepositoryImpl
import com.paawan.panchang.domain.repository.FestivalRepository
import com.paawan.panchang.domain.repository.PanchangRepository
import com.paawan.panchang.domain.repository.VratRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPanchangRepository(
        impl: PanchangRepositoryImpl
    ): PanchangRepository

    @Binds
    @Singleton
    abstract fun bindFestivalRepository(
        impl: FestivalRepositoryImpl
    ): FestivalRepository

    @Binds
    @Singleton
    abstract fun bindVratRepository(
        impl: VratRepositoryImpl
    ): VratRepository
}

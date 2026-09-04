package com.paawan.panchang.data.remote

import com.paawan.panchang.data.local.db.entity.FestivalEntity
import com.paawan.panchang.data.local.db.entity.PanchangEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface PanchangApiService {
    @GET("api/v1/panchang")
    suspend fun getDailyPanchang(
        @Query("date") dateIso: String,
        @Query("location") location: String
    ): PanchangEntity

    @GET("api/v1/festivals")
    suspend fun getUpcomingFestivals(
        @Query("year") year: Int
    ): List<FestivalEntity>
}

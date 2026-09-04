package com.paawan.panchang.domain.repository

import com.paawan.panchang.domain.model.Festival
import kotlinx.coroutines.flow.Flow

interface FestivalRepository {
    fun getUpcomingFestivals(): Flow<List<Festival>>
    fun getFestivalById(id: String): Flow<Festival?>
    suspend fun refreshFestivals()
}

package com.paawan.panchang.domain.repository

import com.paawan.panchang.domain.model.PanchangInfo
import kotlinx.coroutines.flow.Flow

interface PanchangRepository {
    fun getTodayPanchang(): Flow<PanchangInfo>
    fun getPanchangForDate(dateIso: String): Flow<PanchangInfo>
    suspend fun refreshPanchang()
}

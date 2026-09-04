package com.paawan.panchang.domain.repository

import com.paawan.panchang.domain.model.VratInfo
import kotlinx.coroutines.flow.Flow

interface VratRepository {
    fun getVratList(): Flow<List<VratInfo>>
    fun getVratById(id: String): Flow<VratInfo?>
}

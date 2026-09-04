package com.paawan.panchang.domain.usecase

import com.paawan.panchang.core.util.Resource
import com.paawan.panchang.domain.model.VratInfo
import com.paawan.panchang.domain.repository.VratRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetVratListUseCase @Inject constructor(
    private val repository: VratRepository
) {
    operator fun invoke(): Flow<Resource<List<VratInfo>>> {
        return repository.getVratList()
            .map { list -> Resource.Success(list) as Resource<List<VratInfo>> }
            .catch { e -> emit(Resource.Error(e.localizedMessage ?: "Failed to load vrat list")) }
    }
}

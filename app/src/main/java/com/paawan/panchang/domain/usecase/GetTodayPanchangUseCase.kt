package com.paawan.panchang.domain.usecase

import com.paawan.panchang.core.util.Resource
import com.paawan.panchang.domain.model.PanchangInfo
import com.paawan.panchang.domain.repository.PanchangRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTodayPanchangUseCase @Inject constructor(
    private val repository: PanchangRepository
) {
    operator fun invoke(): Flow<Resource<PanchangInfo>> {
        return repository.getTodayPanchang()
            .map { data -> Resource.Success(data) as Resource<PanchangInfo> }
            .catch { e -> emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred")) }
    }
}

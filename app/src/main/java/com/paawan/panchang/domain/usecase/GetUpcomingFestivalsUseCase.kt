package com.paawan.panchang.domain.usecase

import com.paawan.panchang.core.util.Resource
import com.paawan.panchang.domain.model.Festival
import com.paawan.panchang.domain.repository.FestivalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUpcomingFestivalsUseCase @Inject constructor(
    private val repository: FestivalRepository
) {
    operator fun invoke(): Flow<Resource<List<Festival>>> {
        return repository.getUpcomingFestivals()
            .map { list -> Resource.Success(list) as Resource<List<Festival>> }
            .catch { e -> emit(Resource.Error(e.localizedMessage ?: "Failed to load festivals")) }
    }
}

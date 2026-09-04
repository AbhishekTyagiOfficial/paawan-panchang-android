package com.paawan.panchang.presentation.home

import com.paawan.panchang.domain.model.Festival
import com.paawan.panchang.domain.model.PanchangInfo

data class HomeState(
    val panchang: PanchangInfo? = null,
    val upcomingFestivals: List<Festival> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

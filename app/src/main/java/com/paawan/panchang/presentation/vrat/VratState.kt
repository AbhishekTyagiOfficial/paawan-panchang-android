package com.paawan.panchang.presentation.vrat

import com.paawan.panchang.domain.model.VratInfo

data class VratState(
    val vrats: List<VratInfo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

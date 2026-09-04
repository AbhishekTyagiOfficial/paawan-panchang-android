package com.paawan.panchang.presentation.festival

import com.paawan.panchang.domain.model.Festival

data class FestivalState(
    val festivals: List<Festival> = emptyList(),
    val selectedFestival: Festival? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

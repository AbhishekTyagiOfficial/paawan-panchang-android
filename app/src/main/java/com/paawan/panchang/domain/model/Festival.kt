package com.paawan.panchang.domain.model

data class Festival(
    val id: String,
    val titleEn: String,
    val titleHi: String,
    val dateIso: String,
    val formattedDate: String,
    val tithiName: String,
    val deity: String,
    val category: String,
    val description: String,
    val pujaVidhi: String,
    val muhuratTiming: String,
    val mantras: List<String> = emptyList()
)

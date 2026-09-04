package com.paawan.panchang.domain.model

data class VratInfo(
    val id: String,
    val titleEn: String,
    val titleHi: String,
    val dateIso: String,
    val formattedDate: String,
    val fastingType: String,
    val mahatmya: String,
    val rules: String
)

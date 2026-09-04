package com.paawan.panchang.domain.model

data class PanchangInfo(
    val dateIso: String,
    val formattedDate: String,
    val tithiName: String,
    val paksha: String,
    val nakshatra: String,
    val yoga: String,
    val karana: String,
    val vikramSamvat: Int,
    val sakaSamvat: Int,
    val rahuKalam: String,
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val isSpecialOccasion: Boolean = false,
    val occasionSummary: String = ""
)

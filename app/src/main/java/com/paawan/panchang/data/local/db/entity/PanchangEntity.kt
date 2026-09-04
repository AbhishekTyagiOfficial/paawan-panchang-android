package com.paawan.panchang.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.paawan.panchang.domain.model.PanchangInfo

@Entity(tableName = "panchang_table")
data class PanchangEntity(
    @PrimaryKey val dateIso: String,
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
    val isSpecialOccasion: Boolean,
    val occasionSummary: String
) {
    fun toDomainModel(): PanchangInfo {
        return PanchangInfo(
            dateIso = dateIso,
            formattedDate = formattedDate,
            tithiName = tithiName,
            paksha = paksha,
            nakshatra = nakshatra,
            yoga = yoga,
            karana = karana,
            vikramSamvat = vikramSamvat,
            sakaSamvat = sakaSamvat,
            rahuKalam = rahuKalam,
            sunrise = sunrise,
            sunset = sunset,
            moonrise = moonrise,
            isSpecialOccasion = isSpecialOccasion,
            occasionSummary = occasionSummary
        )
    }
}

package com.paawan.panchang.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.paawan.panchang.domain.model.Festival

@Entity(tableName = "festival_table")
data class FestivalEntity(
    @PrimaryKey val id: String,
    val titleEn: String,
    val titleHi: String,
    val dateIso: String,
    val formattedDate: String,
    val tithiName: String,
    val deity: String,
    val category: String,
    val description: String,
    val pujaVidhi: String,
    val muhuratTiming: String
) {
    fun toDomainModel(): Festival {
        return Festival(
            id = id,
            titleEn = titleEn,
            titleHi = titleHi,
            dateIso = dateIso,
            formattedDate = formattedDate,
            tithiName = tithiName,
            deity = deity,
            category = category,
            description = description,
            pujaVidhi = pujaVidhi,
            muhuratTiming = muhuratTiming
        )
    }
}

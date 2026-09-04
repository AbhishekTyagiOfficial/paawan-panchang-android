package com.paawan.panchang.data.repository

import com.paawan.panchang.domain.model.VratInfo
import com.paawan.panchang.domain.repository.VratRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class VratRepositoryImpl @Inject constructor() : VratRepository {

    override fun getVratList(): Flow<List<VratInfo>> {
        return flowOf(getSampleVrats())
    }

    override fun getVratById(id: String): Flow<VratInfo?> {
        return flowOf(getSampleVrats().find { it.id == id })
    }

    private fun getSampleVrats(): List<VratInfo> {
        return listOf(
            VratInfo(
                id = "vrat_1",
                titleEn = "Parivartini Ekadashi",
                titleHi = "परिवर्तिनी एकादशी",
                dateIso = "2026-09-21",
                formattedDate = "Monday, 21 September 2026",
                fastingType = "Full Day Fast (Nirjala / Phalahar)",
                mahatmya = "On this Ekadashi, Lord Vishnu turns his sleeping posture in Kshira Sagara. Fasting grants relief from past sins and bestows Moksha.",
                rules = "Avoid rice, grains, and non-veg. Consume milk, fruits, and Sabudana. Perform Vishnu Sahasranama recital."
            ),
            VratInfo(
                id = "vrat_2",
                titleEn = "Pradosh Vrat (Som Pradosh)",
                titleHi = "प्रदोष व्रत",
                dateIso = "2026-09-23",
                formattedDate = "Wednesday, 23 September 2026",
                fastingType = "Evening Pradosh Kaal Fast",
                mahatmya = "Pradosh Vrat dedicated to Lord Shiva and Goddess Parvati in twilight hours. Brings peace, health, and fulfillment of desires.",
                rules = "Fast until sunset. Perform Shiva Abhishekam with Panchamrit during Pradosh Kaal (sunset to 1.5 hrs later)."
            ),
            VratInfo(
                id = "vrat_3",
                titleEn = "Sankashti Chaturthi Vrat",
                titleHi = "संकष्टी चतुर्थी व्रत",
                dateIso = "2026-09-28",
                formattedDate = "Monday, 28 September 2026",
                fastingType = "Moonrise Fasting",
                mahatmya = "Special day for worshipping Lord Ganesha to remove obstacles and troubles from family life.",
                rules = "Fast whole day until sighting the Moon. Offer Arghya to Chandra Dev and Ganesha before breaking fast."
            )
        )
    }
}

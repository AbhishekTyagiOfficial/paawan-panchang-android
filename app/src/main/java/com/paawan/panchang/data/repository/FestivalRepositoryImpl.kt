package com.paawan.panchang.data.repository

import com.paawan.panchang.data.local.db.dao.FestivalDao
import com.paawan.panchang.data.local.db.entity.FestivalEntity
import com.paawan.panchang.domain.model.Festival
import com.paawan.panchang.domain.repository.FestivalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FestivalRepositoryImpl @Inject constructor(
    private val dao: FestivalDao
) : FestivalRepository {

    override fun getUpcomingFestivals(): Flow<List<Festival>> {
        return dao.getAllFestivals().map { entities ->
            if (entities.isEmpty()) {
                getSampleFestivals()
            } else {
                entities.map { it.toDomainModel() }
            }
        }
    }

    override fun getFestivalById(id: String): Flow<Festival?> {
        return dao.getFestivalById(id).map { entity ->
            entity?.toDomainModel() ?: getSampleFestivals().find { it.id == id }
        }
    }

    override suspend fun refreshFestivals() {
        val sampleEntities = getSampleFestivals().map {
            FestivalEntity(
                id = it.id,
                titleEn = it.titleEn,
                titleHi = it.titleHi,
                dateIso = it.dateIso,
                formattedDate = it.formattedDate,
                tithiName = it.tithiName,
                deity = it.deity,
                category = it.category,
                description = it.description,
                pujaVidhi = it.pujaVidhi,
                muhuratTiming = it.muhuratTiming
            )
        }
        dao.insertFestivals(sampleEntities)
    }

    private fun getSampleFestivals(): List<Festival> {
        return listOf(
            Festival(
                id = "fest_1",
                titleEn = "Ganesh Chaturthi",
                titleHi = "गणेश चतुर्थी",
                dateIso = "2026-09-14",
                formattedDate = "Monday, 14 September 2026",
                tithiName = "Bhadrapada Shukla Chaturthi",
                deity = "Lord Ganesha",
                category = "Major Festival",
                description = "Ganesh Chaturthi marks the birth anniversary of Lord Ganesha, the god of wisdom, prosperity, and auspicious beginnings.",
                pujaVidhi = "Establish Ganesha idol in Ishan Kon, offer Modak, Durva grass, red flowers, perform Aarti and chant Atharvashirsha.",
                muhuratTiming = "11:03 AM to 01:34 PM",
                mantras = listOf("Om Gan Ganapataye Namaha", "Vakratunda Mahakaya Surya Koti Samaprabha")
            ),
            Festival(
                id = "fest_2",
                titleEn = "Anant Chaturdashi",
                titleHi = "अनंत चतुर्दशी",
                dateIso = "2026-09-26",
                formattedDate = "Saturday, 26 September 2026",
                tithiName = "Bhadrapada Shukla Chaturdashi",
                deity = "Lord Vishnu & Lord Ganesha",
                category = "Vrat & Visarjan",
                description = "Dedicated to Lord Ananta (Vishnu) and Ganesha Visarjan after 10 days of Ganeshotsav.",
                pujaVidhi = "Tie Anant Sutra with 14 knots on right arm, worship Lord Vishnu, perform Ganesha Visarjan with grand procession.",
                muhuratTiming = "06:12 AM to 07:40 AM",
                mantras = listOf("Om Anantaya Namaha", "Shantakaram Bhujagashayanam")
            ),
            Festival(
                id = "fest_3",
                titleEn = "Pitru Paksha Begins",
                titleHi = "पितृ पक्ष प्रारंभ",
                dateIso = "2026-09-27",
                formattedDate = "Sunday, 27 September 2026",
                tithiName = "Bhadrapada Purnima / Bhadrapada Pratipada",
                deity = "Ancestors (Pitru Devatayein)",
                category = "Shraddh Paksha",
                description = "16-day sacred fortnight dedicated to offering Shraddh, Tarpan, and Pind Daan to ancestors.",
                pujaVidhi = "Offer Water & Sesame seeds (Tarpan) facing South direction in afternoon (Kutapa Kaal).",
                muhuratTiming = "11:45 AM to 12:32 PM (Kutapa Kaal)",
                mantras = listOf("Om Pitrubhyo Namaha", "Devatabhya Pitrubhyascha Mahayogibhye Eva Cha")
            ),
            Festival(
                id = "fest_4",
                titleEn = "Shardiya Navratri",
                titleHi = "शारदीय नवरात्रि",
                dateIso = "2026-10-10",
                formattedDate = "Saturday, 10 October 2026",
                tithiName = "Ashvin Shukla Pratipada",
                deity = "Goddess Durga",
                category = "Navratri",
                description = "Nine auspicious nights dedicated to worshipping the nine forms of Goddess Durga (Navadurga).",
                pujaVidhi = "Ghatasthapana in morning, fast daily, recite Durga Saptashati, and offer red Chunri & Bhog.",
                muhuratTiming = "06:21 AM to 07:45 AM",
                mantras = listOf("Sarva Mangala Mangalye Shive Sarvartha Sadhike", "Om Dum Durgayei Namaha")
            ),
            Festival(
                id = "fest_5",
                titleEn = "Maha Navami",
                titleHi = "महानवमी (कन्या पूजन)",
                dateIso = "2026-10-18",
                formattedDate = "Sunday, 18 October 2026",
                tithiName = "Ashvin Shukla Navami",
                deity = "Goddess Siddhidatri",
                category = "Navratri",
                description = "Ninth day of Navratri celebrating the worshipping of 9 young girls (Kanya Pujan) and offering Puri, Chana & Halwa Bhog.",
                pujaVidhi = "Wash feet of young girls, apply Tilak, offer Halwa-Puri Bhog, and seek blessings with Dakshina.",
                muhuratTiming = "06:25 AM to 10:15 AM",
                mantras = listOf("Om Hreem Saha Siddhidatryai Namaha")
            ),
            Festival(
                id = "fest_6",
                titleEn = "Dussehra (Vijayadashami)",
                titleHi = "विजयादशमी (दशहरा)",
                dateIso = "2026-10-19",
                formattedDate = "Monday, 19 October 2026",
                tithiName = "Ashvin Shukla Dashami",
                deity = "Lord Rama & Goddess Durga",
                category = "Major Festival",
                description = "Celebrates victory of good over evil - Lord Rama's victory over Ravana and Goddess Durga's victory over Mahishasura.",
                pujaVidhi = "Shami Tree Pujan, Aparajita Pujan, Vehicle & Weapon (Shastra) Pujan.",
                muhuratTiming = "01:58 PM to 02:44 PM (Vijay Muhurat)",
                mantras = listOf("Sri Rama Rama Rameti Rame Rame Manorame", "Om Aparajitayai Namaha")
            ),
            Festival(
                id = "fest_7",
                titleEn = "Karwa Chauth",
                titleHi = "करवा चौथ",
                dateIso = "2026-10-27",
                formattedDate = "Tuesday, 27 October 2026",
                tithiName = "Kartik Krishna Chaturthi",
                deity = "Goddess Karwa & Lord Shiva",
                category = "Vrat",
                description = "Nirjala fast observed by married women for the longevity, health, and prosperity of their husbands.",
                pujaVidhi = "Surgahi before sunrise, listen to Karwa Chauth Katha in evening, offer Arghya to Moon via sieve.",
                muhuratTiming = "05:38 PM to 06:56 PM (Moonrise: 08:12 PM)",
                mantras = listOf("Om Namah Shivaya", "Om Shri Ganeshayei Namaha")
            ),
            Festival(
                id = "fest_8",
                titleEn = "Dhanteras",
                titleHi = "धनतेरस (धन्वंतरि जयंती)",
                dateIso = "2026-11-06",
                formattedDate = "Friday, 06 November 2026",
                tithiName = "Kartik Krishna Trayodashi",
                deity = "Lord Dhanvantari, Lord Kuber & Lakshmi",
                category = "Deepawali",
                description = "First day of Deepawali Mahotsav celebrating health, wealth, and purchasing metals, utensils, or gold.",
                pujaVidhi = "Light Yamadeepam outside home in evening, worship Lord Dhanvantari and Kuber with sweets.",
                muhuratTiming = "06:15 PM to 08:11 PM",
                mantras = listOf("Om Namo Bhagavate Vasudevaya Dhanvantaraye Namaha")
            ),
            Festival(
                id = "fest_9",
                titleEn = "Diwali (Deepawali)",
                titleHi = "दीपावली",
                dateIso = "2026-11-08",
                formattedDate = "Sunday, 08 November 2026",
                tithiName = "Kartik Amavasya",
                deity = "Goddess Lakshmi & Lord Ganesha",
                category = "Festival of Lights",
                description = "The grand festival of lights celebrating Lord Rama's return to Ayodhya and Goddess Lakshmi Pujan.",
                pujaVidhi = "Clean home, draw Rangoli, perform Lakshmi-Ganesha Pujan in Pradosh Kaal, light earthen Diya lamps.",
                muhuratTiming = "05:32 PM to 07:28 PM",
                mantras = listOf("Om Shreem Hreem Shreem Kamale Kamalalaye Praseeda", "Om Shreem Maha Lakshmiyei Namaha")
            ),
            Festival(
                id = "fest_10",
                titleEn = "Govardhan Puja",
                titleHi = "गोवर्धन पूजा (अन्नकूट)",
                dateIso = "2026-11-09",
                formattedDate = "Monday, 09 November 2026",
                tithiName = "Kartik Shukla Pratipada",
                deity = "Lord Krishna (Giriraj Ji)",
                category = "Deepawali",
                description = "Celebrates Lord Krishna lifting Govardhan Parvat to protect villagers from torrential rain.",
                pujaVidhi = "Make Govardhan shape from cow dung, offer 56 Bhog (Annakut), perform Parikrama.",
                muhuratTiming = "06:38 AM to 08:48 AM",
                mantras = listOf("Govardhan Dhari Krishna Murari", "Om Namo Bhagavate Vasudevaya")
            ),
            Festival(
                id = "fest_11",
                titleEn = "Chhath Puja",
                titleHi = "छठ पूजा (सूर्य षष्ठी)",
                dateIso = "2026-11-14",
                formattedDate = "Saturday, 14 November 2026",
                tithiName = "Kartik Shukla Shasthi",
                deity = "Surya Dev & Chhathi Maiya",
                category = "Major Vrat",
                description = "Ancient 4-day festival dedicated to Sun God (Surya Dev) and Chhathi Maiya for family well-being.",
                pujaVidhi = "Offer Evening Arghya (Sandhya Arghya) to setting Sun and Morning Arghya (Usha Arghya) to rising Sun in river.",
                muhuratTiming = "Sunset Arghya: 05:27 PM | Sunrise Arghya: 06:44 AM",
                mantras = listOf("Om Suryaya Namaha", "Om Ghrini Suryaya Namaha")
            )
        )
    }
}

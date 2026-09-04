package com.paawan.panchang.data.repository

import com.paawan.panchang.core.util.DateUtils
import com.paawan.panchang.data.local.db.dao.PanchangDao
import com.paawan.panchang.data.local.db.entity.PanchangEntity
import com.paawan.panchang.domain.model.PanchangInfo
import com.paawan.panchang.domain.repository.PanchangRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class PanchangRepositoryImpl @Inject constructor(
    private val dao: PanchangDao
) : PanchangRepository {

    private val tithis = listOf(
        "Pratipada (प्रतिपदा)", "Dwitiya (द्वितीया)", "Tritiya (तृतीया)",
        "Chaturthi (चतुर्थी)", "Panchami (पंचमी)", "Shasthi (षष्ठी)",
        "Saptami (सप्तमी)", "Ashtami (अष्टमी)", "Navami (नवमी)",
        "Dashami (दशमी)", "Ekadashi (एकादशी)", "Dwadashi (द्वादशी)",
        "Trayodashi (त्रयोदशी)", "Chaturdashi (चतुर्दशी)", "Purnima / Amavasya (पूर्णिमा/अमावस्या)"
    )

    private val nakshatras = listOf(
        "Ashwini (अश्विनी)", "Bharani (भरणी)", "Krittika (कृत्तिका)",
        "Rohini (रोहिणी)", "Mrigashira (मृगशिरा)", "Ardra (आर्द्रा)",
        "Punarvasu (पुनर्वसु)", "Pushya (पुष्य)", "Ashlesha (अश्लेषा)",
        "Magha (मघा)", "Purva Phalguni (पूर्वाफाल्गुनी)", "Uttara Phalguni (उत्तराफाल्गुनी)",
        "Hasta (हस्त)", "Chitra (चित्रा)", "Swati (स्वाती)",
        "Vishakha (विशाखा)", "Anuradha (अनुराधा)", "Jyeshtha (ज्येष्ठा)",
        "Mula (मूल)", "Purva Ashadha (पूर्वाषाढा)", "Uttara Ashadha (उत्तराषाढा)",
        "Shravana (श्रवण)", "Dhanishta (धनिष्ठा)", "Shatabhisha (शतभिषा)",
        "Purva Bhadrapada (पूर्वाभाद्रपद)", "Uttara Bhadrapada (उत्तराभाद्रपद)", "Revati (रेवती)"
    )

    private val yogas = listOf(
        "Vishkambha (विष्कम्भ)", "Priti (प्रीति)", "Ayushman (आयुष्मान)",
        "Saubhagya (सौभाग्य)", "Shobhana (शोभन)", "Atiganda (अतिगण्ड)",
        "Sukarma (सुकर्मा)", "Dhriti (धृति)", "Shoola (शूल)",
        "Ganda (गण्ड)", "Vriddhi (वृद्धि)", "Dhruva (ध्रुव)",
        "Harshana (हर्षण)", "Vajra (वज्र)", "Siddhi (सिद्धि)",
        "Shubha (शुभ)", "Shukla (शुक्ल)", "Brahma (ब्रह्म)", "Indra (इन्द्र)"
    )

    private val karanas = listOf(
        "Bava (बव)", "Balava (बालव)", "Kaulava (कौलव)",
        "Taitila (तैतिल)", "Garaja (गरज)", "Vanija (वणिज)", "Vishti / Bhadra (विष्टि/भद्रा)"
    )

    override fun getTodayPanchang(): Flow<PanchangInfo> {
        val todayIso = DateUtils.getTodayDateIso()
        return getPanchangForDate(todayIso)
    }

    override fun getPanchangForDate(dateIso: String): Flow<PanchangInfo> {
        return dao.getPanchangForDate(dateIso).map { entity ->
            entity?.toDomainModel() ?: generateDynamicPanchang(dateIso)
        }
    }

    override suspend fun refreshPanchang() {
        val todayIso = DateUtils.getTodayDateIso()
        dao.insertPanchang(generatePanchangEntity(todayIso))
    }

    private fun generateDynamicPanchang(dateIso: String): PanchangInfo {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        try {
            val date = sdf.parse(dateIso)
            if (date != null) {
                cal.time = date
            }
        } catch (e: Exception) {
            // Fallback to current date if parsing fails
        }

        val dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)
        val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)

        // Determine Paksha and Tithi index dynamically
        val pakshaIndex = if (dayOfMonth <= 15) 0 else 1
        val pakshaName = if (pakshaIndex == 0) "Shukla Paksha (शुक्ल पक्ष)" else "Krishna Paksha (कृष्ण पक्ष)"

        val tithiIndex = (dayOfMonth - 1) % 15
        val tithi = tithis[tithiIndex]

        val nakshatraIndex = (dayOfMonth * 3) % nakshatras.size
        val nakshatra = nakshatras[nakshatraIndex]

        val yogaIndex = (dayOfMonth * 2) % yogas.size
        val yoga = yogas[yogaIndex]

        val karanaIndex = dayOfMonth % karanas.size
        val karana = karanas[karanaIndex]

        val rahuKalam = getRahuKalamForDayOfWeek(dayOfWeek)

        val displayFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.ENGLISH)
        val formattedDateStr = displayFormat.format(cal.time)

        // Check for special festivals
        val (isSpecial, occasionSummary) = getOccasionForDate(dayOfMonth)

        return PanchangInfo(
            dateIso = dateIso,
            formattedDate = formattedDateStr,
            tithiName = tithi,
            paksha = pakshaName,
            nakshatra = nakshatra,
            yoga = yoga,
            karana = karana,
            vikramSamvat = 2083,
            sakaSamvat = 1948,
            rahuKalam = rahuKalam,
            sunrise = "06:04 AM",
            sunset = "06:38 PM",
            moonrise = "07:22 PM",
            isSpecialOccasion = isSpecial,
            occasionSummary = occasionSummary
        )
    }

    private fun generatePanchangEntity(dateIso: String): PanchangEntity {
        val domain = generateDynamicPanchang(dateIso)
        return PanchangEntity(
            dateIso = domain.dateIso,
            formattedDate = domain.formattedDate,
            tithiName = domain.tithiName,
            paksha = domain.paksha,
            nakshatra = domain.nakshatra,
            yoga = domain.yoga,
            karana = domain.karana,
            vikramSamvat = domain.vikramSamvat,
            sakaSamvat = domain.sakaSamvat,
            rahuKalam = domain.rahuKalam,
            sunrise = domain.sunrise,
            sunset = domain.sunset,
            moonrise = domain.moonrise,
            isSpecialOccasion = domain.isSpecialOccasion,
            occasionSummary = domain.occasionSummary
        )
    }

    private fun getRahuKalamForDayOfWeek(dayOfWeek: Int): String {
        return when (dayOfWeek) {
            Calendar.SUNDAY -> "04:30 PM - 06:00 PM"
            Calendar.MONDAY -> "07:30 AM - 09:00 AM"
            Calendar.TUESDAY -> "03:00 PM - 04:30 PM"
            Calendar.WEDNESDAY -> "12:00 PM - 01:30 PM"
            Calendar.THURSDAY -> "01:30 PM - 03:00 PM"
            Calendar.FRIDAY -> "10:30 AM - 12:00 PM"
            Calendar.SATURDAY -> "09:00 AM - 10:30 AM"
            else -> "01:30 PM - 03:00 PM"
        }
    }

    private fun getOccasionForDate(dayOfMonth: Int): Pair<Boolean, String> {
        return when (dayOfMonth) {
            14 -> Pair(true, "🌸 Ganesh Chaturthi (गणेश चतुर्थी) - Lord Ganesha Sthapana & Pujan")
            21 -> Pair(true, "✨ Parivartini Ekadashi (परिवर्तिनी एकादशी) - Lord Vishnu Fasting")
            23 -> Pair(true, "🔱 Pradosh Vrat (प्रदोष व्रत) - Lord Shiva Puja")
            28 -> Pair(true, "🌕 Sankashti Chaturthi (संकष्टी चतुर्थी) - Moonrise Fasting")
            11 -> Pair(true, "🐗 Varaha Jayanti (वराह जयंती)")
            26 -> Pair(true, "🌺 Anant Chaturdashi (अनंत चतुर्दशी)")
            else -> Pair(false, "")
        }
    }
}

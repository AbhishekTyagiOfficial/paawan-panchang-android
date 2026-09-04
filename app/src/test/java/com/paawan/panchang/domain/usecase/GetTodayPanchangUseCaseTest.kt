package com.paawan.panchang.domain.usecase

import com.paawan.panchang.core.util.Resource
import com.paawan.panchang.domain.model.PanchangInfo
import com.paawan.panchang.domain.repository.PanchangRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetTodayPanchangUseCaseTest {

    private val fakePanchangInfo = PanchangInfo(
        dateIso = "2026-09-04",
        formattedDate = "Friday, 04 September 2026",
        tithiName = "Navami",
        paksha = "Shukla Paksha",
        nakshatra = "Rohini",
        yoga = "Saubhagya",
        karana = "Bava",
        vikramSamvat = 2083,
        sakaSamvat = 1948,
        rahuKalam = "10:45 AM - 12:20 PM",
        sunrise = "06:05 AM",
        sunset = "06:42 PM",
        moonrise = "07:15 PM"
    )

    private val fakeRepository = object : PanchangRepository {
        override fun getTodayPanchang() = flowOf(fakePanchangInfo)
        override fun getPanchangForDate(dateIso: String) = flowOf(fakePanchangInfo)
        override suspend fun refreshPanchang() {}
    }

    @Test
    fun `invoke returns success resource with valid panchang data`() = runTest {
        val useCase = GetTodayPanchangUseCase(fakeRepository)
        val result = useCase().first()

        assertTrue(result is Resource.Success)
        assertEquals("Navami", result.data?.tithiName)
        assertEquals(2083, result.data?.vikramSamvat)
    }
}

package com.paawan.panchang.presentation.calendar

import com.paawan.panchang.domain.model.PanchangInfo

data class CalendarState(
    val currentYear: Int = 2026,
    val currentMonth: Int = 8, // 0 = Jan, 8 = Sep
    val monthTitle: String = "September 2026",
    val hinduMonthTitle: String = "भाद्रपद - आश्विन 2083 (विक्रम संवत)",
    val totalDays: Int = 30,
    val firstDayOffset: Int = 2,
    val selectedDateIso: String = "2026-09-04",
    val panchang: PanchangInfo? = null,
    val isLoading: Boolean = false
)

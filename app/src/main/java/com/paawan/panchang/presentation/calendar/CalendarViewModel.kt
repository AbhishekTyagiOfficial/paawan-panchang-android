package com.paawan.panchang.presentation.calendar

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paawan.panchang.core.util.DateUtils
import com.paawan.panchang.domain.repository.PanchangRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repository: PanchangRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val KEY_SELECTED_DATE = "selected_date_iso"
    }

    private val _state = MutableStateFlow(CalendarState())
    val state: StateFlow<CalendarState> = _state.asStateFlow()

    init {
        resetToToday()
    }

    fun resetToToday() {
        val cal = Calendar.getInstance()
        updateMonthState(cal, selectToday = true)
    }

    fun previousMonth() {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, _state.value.currentYear)
        cal.set(Calendar.MONTH, _state.value.currentMonth)
        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.add(Calendar.MONTH, -1)
        updateMonthState(cal, selectToday = false)
    }

    fun nextMonth() {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, _state.value.currentYear)
        cal.set(Calendar.MONTH, _state.value.currentMonth)
        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.add(Calendar.MONTH, 1)
        updateMonthState(cal, selectToday = false)
    }

    private fun updateMonthState(cal: Calendar, selectToday: Boolean) {
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH) // 0..11
        val totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH)

        val firstDayCal = Calendar.getInstance()
        firstDayCal.set(year, month, 1)
        val firstDayOfWeek = firstDayCal.get(Calendar.DAY_OF_WEEK)
        val firstDayOffset = firstDayOfWeek - 1 // 0 for Sunday

        val monthSdf = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
        val monthTitle = monthSdf.format(firstDayCal.time)
        val hinduMonthTitle = getHinduMonthName(month, year)

        val targetDay = if (selectToday) {
            val todayCal = Calendar.getInstance()
            if (todayCal.get(Calendar.YEAR) == year && todayCal.get(Calendar.MONTH) == month) {
                todayCal.get(Calendar.DAY_OF_MONTH)
            } else 1
        } else 1

        val monthStr = if (month + 1 < 10) "0${month + 1}" else "${month + 1}"
        val dayStr = if (targetDay < 10) "0$targetDay" else "$targetDay"
        val dateIso = "$year-$monthStr-$dayStr"

        _state.value = _state.value.copy(
            currentYear = year,
            currentMonth = month,
            monthTitle = monthTitle,
            hinduMonthTitle = hinduMonthTitle,
            totalDays = totalDays,
            firstDayOffset = firstDayOffset,
            selectedDateIso = dateIso
        )

        selectDate(dateIso)
    }

    fun selectDate(dateIso: String) {
        savedStateHandle[KEY_SELECTED_DATE] = dateIso
        _state.value = _state.value.copy(selectedDateIso = dateIso, isLoading = true)
        repository.getPanchangForDate(dateIso).onEach { panchang ->
            _state.value = _state.value.copy(panchang = panchang, isLoading = false)
        }.launchIn(viewModelScope)
    }

    private fun getHinduMonthName(month: Int, year: Int): String {
        val vikramYear = year + 57
        val names = listOf(
            "पौष - माघ", "माघ - फाल्गुन", "फाल्गुन - चैत्र",
            "चैत्र - वैशाख", "वैशाख - ज्येष्ठ", "ज्येष्ठ - आषाढ़",
            "आषाढ़ - श्रावण", "श्रावण - भाद्रपद", "भाद्रपद - आश्विन",
            "आश्विन - कार्तिक", "कार्तिक - मार्गशीर्ष", "मार्गशीर्ष - पौष"
        )
        val name = names.getOrElse(month) { "भाद्रपद - आश्विन" }
        return "$name $vikramYear (विक्रम संवत)"
    }
}

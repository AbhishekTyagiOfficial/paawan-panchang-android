package com.paawan.panchang.core.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    fun getTodayFormatted(): String {
        val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.ENGLISH)
        return sdf.format(Date())
    }

    fun getTodayDateIso(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }

    fun getHindiFormattedDate(date: Date = Date()): String {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("hi", "IN"))
        return sdf.format(date)
    }
}

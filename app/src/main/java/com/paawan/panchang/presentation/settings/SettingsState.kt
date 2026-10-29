package com.paawan.panchang.presentation.settings

data class SettingsState(
    val isDarkMode: Boolean? = null,
    val location: String = "New Delhi, India",
    val language: String = "hi",
    val isOldDesign: Boolean = false
)

package com.paawan.panchang.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Calendar : Screen("calendar", "Calendar", Icons.Default.CalendarMonth)
    object Festival : Screen("festival", "Festivals", Icons.Default.Celebration)
    object Vrat : Screen("vrat", "Vrat", Icons.Default.SelfImprovement)
    object Settings : Screen("settings", "Settings", Icons.Default.Settings)
}

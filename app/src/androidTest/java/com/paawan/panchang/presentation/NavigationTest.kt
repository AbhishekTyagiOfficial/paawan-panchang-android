package com.paawan.panchang.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.paawan.panchang.core.theme.PaawanPanchangTheme
import com.paawan.panchang.presentation.navigation.PaawanNavGraph
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun app_launches_and_bottom_navigation_bar_is_visible() {
        composeTestRule.setContent {
            PaawanPanchangTheme {
                PaawanNavGraph()
            }
        }

        // Verify bottom navigation items exist
        composeTestRule.onNodeWithText("Home").assertExists()
        composeTestRule.onNodeWithText("Calendar").assertExists()
        composeTestRule.onNodeWithText("Festivals").assertExists()
        composeTestRule.onNodeWithText("Vrat").assertExists()
        composeTestRule.onNodeWithText("Settings").assertExists()
    }
}

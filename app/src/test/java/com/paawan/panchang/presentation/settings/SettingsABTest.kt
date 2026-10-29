package com.paawan.panchang.presentation.settings

import com.paawan.panchang.data.local.datastore.UserPreferencesRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class SettingsABTest {

    @Test
    fun `verify AB testing preference key name is Old_Design`() {
        assertEquals("Old_Design", UserPreferencesRepository.KEY_OLD_DESIGN.name)
    }

    @Test
    fun `verify SettingsState defaults to false for Old_Design`() {
        val state = SettingsState()
        assertFalse(state.isOldDesign)
    }
}

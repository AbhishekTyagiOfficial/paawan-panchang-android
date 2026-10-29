package com.paawan.panchang.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

@Singleton
class UserPreferencesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        val KEY_DARK_MODE = booleanPreferencesKey("dark_mode")
        val KEY_LOCATION = stringPreferencesKey("user_location")
        val KEY_LANGUAGE = stringPreferencesKey("user_language")
        val KEY_OLD_DESIGN = booleanPreferencesKey("Old_Design")
    }

    val isDarkMode: Flow<Boolean?> = context.dataStore.data.map { prefs ->
        prefs[KEY_DARK_MODE]
    }

    val userLocation: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[KEY_LOCATION] ?: "New Delhi, India"
    }

    val userLanguage: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[KEY_LANGUAGE] ?: "hi"
    }

    val isOldDesign: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_OLD_DESIGN] ?: false
    }

    suspend fun setDarkMode(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_DARK_MODE] = enabled
        }
    }

    suspend fun setLocation(location: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_LOCATION] = location
        }
    }

    suspend fun setLanguage(languageCode: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_LANGUAGE] = languageCode
        }
    }

    suspend fun setOldDesign(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_OLD_DESIGN] = enabled
        }
    }
}

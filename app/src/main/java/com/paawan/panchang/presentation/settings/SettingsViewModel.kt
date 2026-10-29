package com.paawan.panchang.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paawan.panchang.data.local.datastore.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                preferencesRepository.isDarkMode,
                preferencesRepository.userLocation,
                preferencesRepository.userLanguage,
                preferencesRepository.isOldDesign
            ) { isDark, location, lang, oldDesign ->
                SettingsState(
                    isDarkMode = isDark,
                    location = location,
                    language = lang,
                    isOldDesign = oldDesign
                )
            }.collect { newState ->
                _state.value = newState
            }
        }
    }

    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            preferencesRepository.setDarkMode(enabled)
        }
    }

    fun updateLocation(newLocation: String) {
        viewModelScope.launch {
            preferencesRepository.setLocation(newLocation)
        }
    }

    fun toggleOldDesign(enabled: Boolean) {
        viewModelScope.launch {
            preferencesRepository.setOldDesign(enabled)
        }
    }
}

package com.paawan.panchang.presentation.festival

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paawan.panchang.core.util.Resource
import com.paawan.panchang.domain.model.Festival
import com.paawan.panchang.domain.usecase.GetUpcomingFestivalsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FestivalViewModel @Inject constructor(
    private val getUpcomingFestivalsUseCase: GetUpcomingFestivalsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FestivalState())
    val state: StateFlow<FestivalState> = _state.asStateFlow()

    init {
        loadFestivals()
    }

    fun loadFestivals() {
        getUpcomingFestivalsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        festivals = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message,
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun selectFestival(festival: Festival?) {
        _state.value = _state.value.copy(selectedFestival = festival)
    }
}

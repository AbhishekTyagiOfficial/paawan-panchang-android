package com.paawan.panchang.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paawan.panchang.core.util.Resource
import com.paawan.panchang.domain.usecase.GetTodayPanchangUseCase
import com.paawan.panchang.domain.usecase.GetUpcomingFestivalsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTodayPanchangUseCase: GetTodayPanchangUseCase,
    private val getUpcomingFestivalsUseCase: GetUpcomingFestivalsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadHomeData()
    }

    fun loadHomeData() {
        getTodayPanchangUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        panchang = result.data,
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

        getUpcomingFestivalsUseCase().onEach { result ->
            if (result is Resource.Success) {
                _state.value = _state.value.copy(
                    upcomingFestivals = result.data ?: emptyList()
                )
            }
        }.launchIn(viewModelScope)
    }
}

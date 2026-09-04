package com.paawan.panchang.presentation.vrat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paawan.panchang.core.util.Resource
import com.paawan.panchang.domain.usecase.GetVratListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VratViewModel @Inject constructor(
    private val getVratListUseCase: GetVratListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(VratState())
    val state: StateFlow<VratState> = _state.asStateFlow()

    init {
        loadVrats()
    }

    fun loadVrats() {
        getVratListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        vrats = result.data ?: emptyList(),
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
}

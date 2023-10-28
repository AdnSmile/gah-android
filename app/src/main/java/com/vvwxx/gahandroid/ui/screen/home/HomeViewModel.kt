package com.vvwxx.gahandroid.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvwxx.gahandroid.data.HotelRepository
import com.vvwxx.gahandroid.data.model.JenisKamar
import com.vvwxx.gahandroid.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HotelRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<JenisKamar>>>
        = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<JenisKamar>>>
        get() = _uiState

    fun getAllJenisKamar() {

        viewModelScope.launch {
            repository.getAllJenisKamar()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    _uiState.value = UiState.Success(data)
                }
        }
    }
}
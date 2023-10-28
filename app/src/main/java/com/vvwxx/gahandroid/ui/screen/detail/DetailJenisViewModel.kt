package com.vvwxx.gahandroid.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvwxx.gahandroid.data.HotelRepository
import com.vvwxx.gahandroid.data.model.JenisKamar
import com.vvwxx.gahandroid.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailJenisViewModel(
    private val repository: HotelRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<JenisKamar>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<JenisKamar>>
        get() = _uiState

    fun getJenisKamar(id: Int) {

        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getJenisKamarById(id))
        }
    }
}
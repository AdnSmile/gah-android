package com.vvwxx.gahandroid.ui.screen.reservasi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvwxx.gahandroid.data.HotelRepository
import com.vvwxx.gahandroid.data.remote.response.AvailabilityKamarResponse
import com.vvwxx.gahandroid.data.remote.response.JenisKamarResponseItem
import com.vvwxx.gahandroid.data.remote.response.WebResponse
import com.vvwxx.gahandroid.ui.common.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReservasiViewModel(
    private val repository: HotelRepository
) : ViewModel() {

    val ketersediaanKamarResponse: StateFlow<UiState<WebResponse<List<AvailabilityKamarResponse>>>> =
        repository.ketersediaanKamarResponse

    val listJenisKamar: StateFlow<UiState<WebResponse<List<JenisKamarResponseItem>>>> =
        repository.listJenisKamar

    val getAccountPref = repository.getAccountPref()

    fun setHargaTerbaru(harga: Int) {
        viewModelScope.launch {
            repository.setHargaTerbaru(harga)
        }
    }

    fun getKetersediaanKamar(token: String, checkin: String, checkout: String, dewasa: Int, anak: Int) {

        viewModelScope.launch {
            repository.getKetersediaanKamar(token, checkin, checkout, dewasa, anak)
        }
    }

    fun getJenisKamar() {
        viewModelScope.launch {
            repository.getJenisKamar()
        }
    }
}
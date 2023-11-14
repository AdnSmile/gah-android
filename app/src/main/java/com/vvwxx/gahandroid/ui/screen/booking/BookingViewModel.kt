package com.vvwxx.gahandroid.ui.screen.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvwxx.gahandroid.data.HotelRepository
import com.vvwxx.gahandroid.data.remote.request.FasilitasRequestItem
import com.vvwxx.gahandroid.data.remote.request.KamarRequestItem
import com.vvwxx.gahandroid.data.remote.response.AddReservasiResponse
import com.vvwxx.gahandroid.data.remote.response.FasilitasResponseItem
import com.vvwxx.gahandroid.data.remote.response.JenisKamarResponseItem
import com.vvwxx.gahandroid.data.remote.response.WebResponse
import com.vvwxx.gahandroid.ui.common.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookingViewModel(
    private val repository: HotelRepository
) : ViewModel() {

    val getAccountPref = repository.getAccountPref()

    val listJenisKamar: StateFlow<UiState<WebResponse<List<JenisKamarResponseItem>>>> =
        repository.listJenisKamar

    val listFasilitas: StateFlow<UiState<WebResponse<List<FasilitasResponseItem>>>> =
        repository.listFasilitas

    val addReservasiResponse: StateFlow<UiState<WebResponse<AddReservasiResponse>>> =
        repository.addReservasiResponse

    fun getJenisKamar() {
        viewModelScope.launch {
            repository.getJenisKamar()
        }
    }

    fun addReservasi(
        token: String, id: Int, checkin: String, checkout: String, dewasa: Int, anak: Int,
        permintaan: String, kamar: Array<KamarRequestItem>, fasilitas: Array<FasilitasRequestItem>
    ) {
        viewModelScope.launch {
            repository.addReservasi(
                token = token,
                id = id,
                checkin = checkin,
                checkout = checkout,
                dewasa = dewasa,
                anak = anak,
                permintaan = permintaan,
                kamar = kamar,
                fasilitas = fasilitas,
            )
        }
    }

    fun getFasilitas(token: String) {
        viewModelScope.launch {
            repository.getFasilitas(token)
        }
    }
}
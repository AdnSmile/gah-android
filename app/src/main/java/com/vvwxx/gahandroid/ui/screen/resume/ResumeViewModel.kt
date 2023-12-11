package com.vvwxx.gahandroid.ui.screen.resume

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvwxx.gahandroid.data.HotelRepository
import com.vvwxx.gahandroid.data.remote.response.DetailReservasiResponse
import com.vvwxx.gahandroid.data.remote.response.WebResponse
import com.vvwxx.gahandroid.ui.common.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResumeViewModel(
    private val repository: HotelRepository
) : ViewModel() {

    val getAccountPref = repository.getAccountPref()

    val detailReservasiResponse : StateFlow<UiState<WebResponse<DetailReservasiResponse>>> =
        repository.detailReservasiResponse

    fun getDetailReservasi(token: String, idReservasi: Int) {
        viewModelScope.launch {
            repository.getDetailReservasi(token, idReservasi)
        }
    }

}
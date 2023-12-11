package com.vvwxx.gahandroid.ui.screen.laporan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvwxx.gahandroid.data.HotelRepository
import com.vvwxx.gahandroid.data.remote.response.CustomerBaruResponse
import com.vvwxx.gahandroid.data.remote.response.PemesanTerbanyakResponse
import com.vvwxx.gahandroid.data.remote.response.WebResponse
import com.vvwxx.gahandroid.ui.common.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LaporanViewModel(
    private val repository: HotelRepository
) : ViewModel() {

    val listCustomerBaru: StateFlow<UiState<WebResponse<List<CustomerBaruResponse>>>>
        = repository.listCustomerBaru

    val listPemesanTerbanyak: StateFlow<UiState<WebResponse<List<PemesanTerbanyakResponse>>>>
        = repository.listPemesanTerbanyak

    val getAccountPref = repository.getAccountPref()

    fun getCustomerBaru(token: String, tahun: Int) {
        viewModelScope.launch {
            repository.getCustomerBaru(token, tahun)
        }
    }

    fun getPemesanTerbanyak(token: String, tahun: Int) {
        viewModelScope.launch {
            repository.getPemesanTerbanyak(token, tahun)
        }
    }

}
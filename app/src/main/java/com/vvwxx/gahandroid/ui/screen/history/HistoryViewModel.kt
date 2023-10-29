package com.vvwxx.gahandroid.ui.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvwxx.gahandroid.data.HotelRepository
import com.vvwxx.gahandroid.data.remote.response.RiwayatReservasiItem
import com.vvwxx.gahandroid.ui.common.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repository: HotelRepository
) : ViewModel() {

    val getAccountPref = repository.getAccountPref()

    val listRiwayatReservasi: StateFlow<UiState<List<RiwayatReservasiItem>>>
        get() = repository.listRiwayatReservasi

    fun getListRiwayatReservasi(token: String, id: Int) {

        viewModelScope.launch {
            repository.getRiwayatReservasi(token, id)
        }
    }
}
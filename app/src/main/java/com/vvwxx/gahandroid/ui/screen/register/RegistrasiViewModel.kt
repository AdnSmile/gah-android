package com.vvwxx.gahandroid.ui.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvwxx.gahandroid.data.HotelRepository
import com.vvwxx.gahandroid.data.remote.response.RegisterResponse
import com.vvwxx.gahandroid.data.remote.response.WebResponse
import com.vvwxx.gahandroid.ui.common.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistrasiViewModel(
    private val repository: HotelRepository
) : ViewModel() {

    val registrasiResponse: StateFlow<UiState<WebResponse<RegisterResponse>>?> =
        repository.registerResponse

    val registerSuccess = repository.registerSuccess

    fun registerAccount(username: String, password: String,
                        nama: String, email: String, noTelpon: String,
                        alamat: String, noIdentitas: String) {

        viewModelScope.launch {
            repository.registerAccount(username, password, nama, email, noTelpon, alamat, noIdentitas)
        }
    }
}
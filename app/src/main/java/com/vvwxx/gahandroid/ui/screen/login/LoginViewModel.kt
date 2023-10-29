package com.vvwxx.gahandroid.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvwxx.gahandroid.data.HotelRepository
import com.vvwxx.gahandroid.data.remote.response.LoginResponse
import com.vvwxx.gahandroid.data.remote.response.WebResponse
import com.vvwxx.gahandroid.ui.common.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: HotelRepository
) : ViewModel() {

    val loginResponse: StateFlow<UiState<WebResponse<LoginResponse>>> =
        repository.loginResponse

    fun loginAccount(email: String, password: String) {
        viewModelScope.launch {
            repository.loginAccount(email, password)
        }
    }
}
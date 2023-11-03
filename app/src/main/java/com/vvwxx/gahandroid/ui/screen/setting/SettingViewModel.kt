package com.vvwxx.gahandroid.ui.screen.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvwxx.gahandroid.data.HotelRepository
import kotlinx.coroutines.launch

class SettingViewModel(private val repository: HotelRepository) : ViewModel() {

    val getAccountPref = repository.getAccountPref()

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}
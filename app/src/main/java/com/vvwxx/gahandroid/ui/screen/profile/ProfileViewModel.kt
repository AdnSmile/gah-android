package com.vvwxx.gahandroid.ui.screen.profile

import androidx.lifecycle.ViewModel
import com.vvwxx.gahandroid.data.HotelRepository

class ProfileViewModel(private val repository: HotelRepository) : ViewModel() {

    val getAccountPref = repository.getAccountPref()
}
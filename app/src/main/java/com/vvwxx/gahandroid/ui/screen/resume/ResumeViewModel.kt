package com.vvwxx.gahandroid.ui.screen.resume

import androidx.lifecycle.ViewModel
import com.vvwxx.gahandroid.data.HotelRepository

class ResumeViewModel(
    private val repository: HotelRepository
) : ViewModel() {

        val getAccountPref = repository.getAccountPref()

}
package com.vvwxx.gahandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vvwxx.gahandroid.data.HotelRepository
import com.vvwxx.gahandroid.ui.screen.detail.DetailJenisViewModel
import com.vvwxx.gahandroid.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: HotelRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailJenisViewModel::class.java)){
            return DetailJenisViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
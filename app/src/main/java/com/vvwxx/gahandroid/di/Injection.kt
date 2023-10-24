package com.vvwxx.gahandroid.di

import com.vvwxx.gahandroid.data.HotelRepository
import com.vvwxx.gahandroid.data.remote.retrofit.ApiConfig

object Injection {

    fun provideRepository(): HotelRepository {

        val apiService = ApiConfig.getApiService()
        return HotelRepository.getInstance(apiService)
    }
}
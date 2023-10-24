package com.vvwxx.gahandroid.data

import com.vvwxx.gahandroid.data.remote.retrofit.ApiService

class HotelRepository(
    private val apiService: ApiService
) {

    companion object {

        @Volatile
        private var instance: HotelRepository? = null

        fun getInstance(
            apiService: ApiService,
        ): HotelRepository =
            instance ?: synchronized(this) {
                instance ?: HotelRepository(apiService)
            }.also { instance = it }
    }
}
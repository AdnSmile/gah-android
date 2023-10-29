package com.vvwxx.gahandroid.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.vvwxx.gahandroid.data.HotelRepository
import com.vvwxx.gahandroid.data.PreferenceRepository
import com.vvwxx.gahandroid.data.remote.retrofit.ApiConfig

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("theDataStore")

object Injection {

    fun provideRepository(context: Context): HotelRepository {

        val apiService = ApiConfig.getApiService()
        val pref = PreferenceRepository.getInstance(context.dataStore)
        return HotelRepository.getInstance(apiService, pref)
    }
}
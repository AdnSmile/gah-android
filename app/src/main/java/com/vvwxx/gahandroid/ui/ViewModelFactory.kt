package com.vvwxx.gahandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vvwxx.gahandroid.data.HotelRepository
import com.vvwxx.gahandroid.ui.screen.booking.BookingViewModel
import com.vvwxx.gahandroid.ui.screen.detail.DetailJenisViewModel
import com.vvwxx.gahandroid.ui.screen.history.HistoryViewModel
import com.vvwxx.gahandroid.ui.screen.home.HomeViewModel
import com.vvwxx.gahandroid.ui.screen.laporan.LaporanViewModel
import com.vvwxx.gahandroid.ui.screen.login.LoginViewModel
import com.vvwxx.gahandroid.ui.screen.profile.ProfileViewModel
import com.vvwxx.gahandroid.ui.screen.register.RegistrasiViewModel
import com.vvwxx.gahandroid.ui.screen.reservasi.ReservasiViewModel
import com.vvwxx.gahandroid.ui.screen.resume.ResumeViewModel
import com.vvwxx.gahandroid.ui.screen.setting.SettingViewModel

class ViewModelFactory(
    private val repository: HotelRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailJenisViewModel::class.java)){
            return DetailJenisViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)){
            return HistoryViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)){
            return SettingViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(RegistrasiViewModel::class.java)){
            return RegistrasiViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(ReservasiViewModel::class.java)){
            return ReservasiViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(BookingViewModel::class.java)){
            return BookingViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(ResumeViewModel::class.java)){
            return ResumeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(LaporanViewModel::class.java)){
            return LaporanViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
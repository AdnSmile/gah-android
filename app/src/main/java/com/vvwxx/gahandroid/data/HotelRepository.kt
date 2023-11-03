package com.vvwxx.gahandroid.data

import com.vvwxx.gahandroid.data.model.DataUmum
import com.vvwxx.gahandroid.data.model.JenisKamar
import com.vvwxx.gahandroid.data.model.Layanan
import com.vvwxx.gahandroid.data.model.Preference
import com.vvwxx.gahandroid.data.model.dummyJenisKamar
import com.vvwxx.gahandroid.data.model.dummyLayanan
import com.vvwxx.gahandroid.data.remote.response.AccountDetailResponse
import com.vvwxx.gahandroid.data.remote.response.LoginResponse
import com.vvwxx.gahandroid.data.remote.response.RegisterResponse
import com.vvwxx.gahandroid.data.remote.response.RiwayatReservasiItem
import com.vvwxx.gahandroid.data.remote.response.WebResponse
import com.vvwxx.gahandroid.data.remote.retrofit.ApiService
import com.vvwxx.gahandroid.ui.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf

class HotelRepository(
    private val apiService: ApiService,
    private val pref: PreferenceRepository
) {

    private val jenisKamar = mutableListOf<JenisKamar>()
    private val layanan = mutableListOf<Layanan>()

    private var dataUmum = MutableStateFlow(DataUmum(emptyList(), emptyList()))

    private val _loginResponse = MutableStateFlow<UiState<WebResponse<LoginResponse>>>(UiState.Loading)
    val loginResponse: StateFlow<UiState<WebResponse<LoginResponse>>>
        get() = _loginResponse

    private val _registerResponse = MutableStateFlow<UiState<WebResponse<RegisterResponse>>?>(UiState.Loading)
    val registerResponse: StateFlow<UiState<WebResponse<RegisterResponse>>?>
        get() = _registerResponse

    private val _accountDetailResponse = MutableStateFlow<UiState<WebResponse<AccountDetailResponse>>>(UiState.Loading)
    val accountDetailResponse: StateFlow<UiState<WebResponse<AccountDetailResponse>>>
        get() = _accountDetailResponse

    private val _listRiwayatReservasi = MutableStateFlow<UiState<List<RiwayatReservasiItem>>>(UiState.Loading)
    val listRiwayatReservasi: StateFlow<UiState<List<RiwayatReservasiItem>>>
        get() = _listRiwayatReservasi

    private val _loginSuccess : MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val loginSuccess : MutableStateFlow<LoginState> get() = _loginSuccess

    private val _registerSuccess : MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState())
    val registerSuccess : MutableStateFlow<RegisterState> get() = _registerSuccess

    init {
        if (jenisKamar.isEmpty()) {
            dummyJenisKamar.forEach {
                jenisKamar.add(
                    JenisKamar(
                        id = it.id,
                        image = it.image,
                        nama = it.nama,
                        deskripsi = it.deskripsi,
                        kapasitas = it.kapasitas,
                        rincianKamar = it.rincianKamar,
                        tipeBed = it.tipeBed,
                        ukuranKamar = it.ukuranKamar,
                        tarif = it.tarif
                    )
                )
            }
        }

        if (layanan.isEmpty()) {
            dummyLayanan.forEach {
                layanan.add(
                    Layanan(
                        id = it.id,
                        image = it.image,
                        nama = it.nama,
                        satuan = it.satuan,
                        tarif = it.tarif
                    )
                )
            }
        }

        dataUmum.value = DataUmum(
            jenisKamar = jenisKamar,
            layanan = layanan
        )
    }

    suspend fun loginAccount(email: String, password: String) {

        _loginResponse.value = UiState.Loading
        try {
            val response = apiService.loginAccount(email, password)
            _loginResponse.value = UiState.Success(response)
            _loginSuccess.value = LoginState(res = response.data )
            _registerSuccess.value = RegisterState()
        } catch (e: Exception) {
            _loginResponse.value = UiState.Error(e.message.toString())
        }
    }

    suspend fun registerAccount(username: String, password: String,
                                nama: String, email: String, noTelpon: String,
                                alamat: String, noIdentitas: String) {

        _registerResponse.value = UiState.Loading
        try {
            val response = apiService.registerAccount(username, password, nama, email, noTelpon, alamat, noIdentitas)
            _registerResponse.value = UiState.Success(response)
            _registerSuccess.value = RegisterState(res = response.data)
        } catch (e: Exception) {
            _loginResponse.value = UiState.Error(e.message.toString())
        }
    }

    suspend fun getAccountDetail(token: String, id: Int) {

        _listRiwayatReservasi.value = UiState.Loading
        try {
            val response = apiService.getAccountDetail("Bearer $token", id)
            _listRiwayatReservasi.value = UiState.Success(response.data.riwayatReservasi)
        } catch (e: Exception) {
            _listRiwayatReservasi.value = UiState.Error(e.message.toString())
        }
    }

    suspend fun getRiwayatReservasi(token: String, id: Int) {

        _accountDetailResponse.value = UiState.Loading
        try {
            val response = apiService.getAccountDetail("Bearer $token", id)
            _accountDetailResponse.value = UiState.Success(response)
        } catch (e: Exception) {
            _accountDetailResponse.value = UiState.Error(e.message.toString())
        }
    }

    suspend fun saveAccountPref(data: Preference) {
        pref.saveAccountPref(data)
    }

    suspend fun logout() {
        _loginSuccess.value = LoginState()
        _registerResponse.value = null
        pref.logout()
    }

    fun getAccountPref(): Flow<Preference> {
        return pref.getAccountPref()
    }

    fun getAllLayanan(): Flow<List<Layanan>> {
        return flowOf(layanan)
    }

    fun getDataUmum(): Flow<DataUmum> {
        return dataUmum.asStateFlow()
    }

    fun getAllJenisKamar(): Flow<List<JenisKamar>> {
        return flowOf(jenisKamar)
    }

    fun getJenisKamarById(id: Int): JenisKamar {

        return jenisKamar.first {
            it.id == id
        }
    }

    companion object {

        @Volatile
        private var instance: HotelRepository? = null

        fun getInstance(
            apiService: ApiService,
            pref: PreferenceRepository
        ): HotelRepository =
            instance ?: synchronized(this) {
                instance ?: HotelRepository(apiService, pref)
            }.also { instance = it }
    }
}

data class LoginState(
    val res: LoginResponse? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)

data class RegisterState(
    val res: RegisterResponse? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
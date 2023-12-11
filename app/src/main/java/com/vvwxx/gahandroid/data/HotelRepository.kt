package com.vvwxx.gahandroid.data

import com.vvwxx.gahandroid.data.model.DataUmum
import com.vvwxx.gahandroid.data.model.JenisKamar
import com.vvwxx.gahandroid.data.model.Layanan
import com.vvwxx.gahandroid.data.model.Preference
import com.vvwxx.gahandroid.data.model.dummyJenisKamar
import com.vvwxx.gahandroid.data.model.dummyLayanan
import com.vvwxx.gahandroid.data.remote.request.FasilitasRequestItem
import com.vvwxx.gahandroid.data.remote.request.KamarRequestItem
import com.vvwxx.gahandroid.data.remote.request.ReservasiRequest
import com.vvwxx.gahandroid.data.remote.response.AccountDetailResponse
import com.vvwxx.gahandroid.data.remote.response.AddReservasiResponse
import com.vvwxx.gahandroid.data.remote.response.AvailabilityKamarResponse
import com.vvwxx.gahandroid.data.remote.response.CustomerBaruResponse
import com.vvwxx.gahandroid.data.remote.response.DetailReservasiResponse
import com.vvwxx.gahandroid.data.remote.response.FasilitasResponseItem
import com.vvwxx.gahandroid.data.remote.response.JenisKamarResponseItem
import com.vvwxx.gahandroid.data.remote.response.LoginResponse
import com.vvwxx.gahandroid.data.remote.response.PemesanTerbanyakResponse
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

    private val _addReservasiSuccess : MutableStateFlow<AddReservasiState> = MutableStateFlow(AddReservasiState())
    val addReservasiSuccess  : MutableStateFlow<AddReservasiState> get() = _addReservasiSuccess

    private val _registerSuccess : MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState())
    val registerSuccess : MutableStateFlow<RegisterState> get() = _registerSuccess

    private val _detailReservasiResponse : MutableStateFlow<UiState<WebResponse<DetailReservasiResponse>>> = MutableStateFlow(UiState.Loading)
    val detailReservasiResponse : StateFlow<UiState<WebResponse<DetailReservasiResponse>>> get() = _detailReservasiResponse

    // check ketersediian kamar
    private val _ketersediaanKamarResponse = MutableStateFlow<UiState<WebResponse<List<AvailabilityKamarResponse>>>>(UiState.Loading)
    val ketersediaanKamarResponse: StateFlow<UiState<WebResponse<List<AvailabilityKamarResponse>>>>
        get() = _ketersediaanKamarResponse

    private val _listJenisKamar = MutableStateFlow<UiState<WebResponse<List<JenisKamarResponseItem>>>>(UiState.Loading)
    val listJenisKamar: StateFlow<UiState<WebResponse<List<JenisKamarResponseItem>>>>
        get() = _listJenisKamar

    private val _listFasilitas = MutableStateFlow<UiState<WebResponse<List<FasilitasResponseItem>>>>(UiState.Loading)
    val listFasilitas: StateFlow<UiState<WebResponse<List<FasilitasResponseItem>>>>
        get() = _listFasilitas

    private val _addReservasiResponse = MutableStateFlow<UiState<WebResponse<AddReservasiResponse>>> (UiState.Loading)
    val addReservasiResponse: StateFlow<UiState<WebResponse<AddReservasiResponse>>>
        get() = _addReservasiResponse

    // laporan
    private val _listCustomerBaru = MutableStateFlow<UiState<WebResponse<List<CustomerBaruResponse>>>>(UiState.Loading)
    val listCustomerBaru: StateFlow<UiState<WebResponse<List<CustomerBaruResponse>>>>
        get() = _listCustomerBaru

    private val _listPemesanTerbanyak = MutableStateFlow<UiState<WebResponse<List<PemesanTerbanyakResponse>>>>(UiState.Loading)
    val listPemesanTerbanyak: StateFlow<UiState<WebResponse<List<PemesanTerbanyakResponse>>>>
        get() = _listPemesanTerbanyak

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

    suspend fun addReservasi(token: String, id: Int, checkin: String, checkout: String, dewasa: Int, anak: Int,
                             permintaan: String, kamar: List<KamarRequestItem>, fasilitas: List<FasilitasRequestItem>) {

        _addReservasiResponse.value = UiState.Loading
        try {
            val response = apiService.addReservasi("Bearer $token", id,
                ReservasiRequest(
                        dewasa, anak, permintaan,
                        checkin, checkout, kamar, fasilitas
                    )
                )
            _addReservasiResponse.value = UiState.Success(response)
            _addReservasiSuccess.value = AddReservasiState(res = response.data)
            _loginSuccess.value = LoginState()
        } catch (e: Exception) {
            _addReservasiResponse.value = UiState.Error(e.message.toString())
        }
    }

    suspend fun getDetailReservasi(token: String, id: Int) {

        _detailReservasiResponse.value = UiState.Loading
        try {
            val response = apiService.getDetailReservasi("Bearer $token", id)
            _detailReservasiResponse.value = UiState.Success(response)
        } catch (e: Exception) {
            _detailReservasiResponse.value = UiState.Error(e.message.toString())
        }
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

    suspend fun getKetersediaanKamar(token: String, checkin: String, checkout: String, dewasa: Int, anak: Int) {

        _ketersediaanKamarResponse.value = UiState.Loading

        try {
            val response = apiService.ketersediaanKamar("Bearer $token", checkin, checkout, dewasa, anak)
            _ketersediaanKamarResponse.value = UiState.Success(response)
        } catch (e: Exception) {
            _ketersediaanKamarResponse.value = UiState.Error(e.message.toString())
        }
    }

    suspend fun getJenisKamar() {
        _listJenisKamar.value = UiState.Loading

        try {
            val response = apiService.getJenisKamar()
            _listJenisKamar.value = UiState.Success(response)
        } catch (e: Exception) {
            _listJenisKamar.value = UiState.Error(e.message.toString())
        }
    }

    suspend fun getPemesanTerbanyak(token: String, tahun: Int) {
        _listPemesanTerbanyak.value = UiState.Loading

        try {
            val response = apiService.getPemesanTerbanyak("Bearer $token", tahun)
            _listPemesanTerbanyak.value = UiState.Success(response)
        } catch (e: Exception) {
            _listPemesanTerbanyak.value = UiState.Error(e.message.toString())
        }
    }

    suspend fun getCustomerBaru(token: String, tahun: Int) {
        _listCustomerBaru.value = UiState.Loading

        try {
            val response = apiService.getCustomerBaru("Bearer $token", tahun)
            _listCustomerBaru.value = UiState.Success(response)
        } catch (e: Exception) {
            _listCustomerBaru.value = UiState.Error(e.message.toString())
        }
    }

    suspend fun getFasilitas(token: String) {
        _listFasilitas.value = UiState.Loading

        try {
            val response = apiService.getFasilitas("Bearer $token")
            _listFasilitas.value = UiState.Success(response)
        } catch (e: Exception) {
            _listFasilitas.value = UiState.Error(e.message.toString())
        }
    }

    suspend fun saveAccountPref(data: Preference) {
        pref.saveAccountPref(data)
    }

    suspend fun setHargaTerbaru(harga: Int) {
        pref.setHargaTerbaru(harga)
    }

    suspend fun setReservasiPref(dewasa: Int, anak: Int, checkin: String, checkout: String) {
        pref.setReservasiPref(dewasa, anak, checkin, checkout)
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

data class AddReservasiState(
    val res: AddReservasiResponse? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
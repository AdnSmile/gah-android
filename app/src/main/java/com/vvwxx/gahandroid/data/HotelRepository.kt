package com.vvwxx.gahandroid.data

import com.vvwxx.gahandroid.data.model.JenisKamar
import com.vvwxx.gahandroid.data.model.dummyJenisKamar
import com.vvwxx.gahandroid.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HotelRepository(
    private val apiService: ApiService
) {

    private val jenisKamar = mutableListOf<JenisKamar>()

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
        ): HotelRepository =
            instance ?: synchronized(this) {
                instance ?: HotelRepository(apiService)
            }.also { instance = it }
    }
}
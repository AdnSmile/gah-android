package com.vvwxx.gahandroid.data.remote.request

import com.google.gson.annotations.SerializedName

data class ReservasiRequest(
    @SerializedName("jumlah_dewasa") val jumlahDewasa: Int,
    @SerializedName("jumlah_anak") val jumlahAnak: Int,
    @SerializedName("permintaan_khusus") val permintaan: String,
    @SerializedName("tgl_checkin") val tglCheckin: String,
    @SerializedName("tgl_checkout") val tglCheckout: String,
    @SerializedName("kamar") val kamar: List<KamarRequestItem>,
    @SerializedName("fasilitas") val fasilitas: List<FasilitasRequestItem>
)
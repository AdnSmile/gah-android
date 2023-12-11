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

//fun createRequestBody(
//    jumlahDewasa: Int,
//    jumlahAnak: Int,
//    permintaan: String,
//    tglCheckin: String,
//    tglCheckout: String,
//    kamar: List<KamarRequestItem>,
//    fasilitas: List<FasilitasRequestItem>
//): RequestBody {
//    val gson = Gson()
//
//    val kamarJson = gson.toJson(kamar)
//    val fasilitasJson = gson.toJson(fasilitas)
//
//    val type = object : TypeToken<Map<String, Any>>() {}.type
//    val payload = mapOf(
//        "jumlah_dewasa" to jumlahDewasa,
//        "jumlah_anak" to jumlahAnak,
//        "permintaan_khusus" to permintaan,
//        "tgl_checkin" to tglCheckin,
//        "tgl_checkout" to tglCheckout,
//        "kamar" to gson.fromJson(kamarJson, type),
//        "fasilitas" to gson.fromJson(fasilitasJson, type)
//    )
//
//    val jsonString = gson.toJson(payload)
//    return RequestBody.create("application/json".toMediaTypeOrNull(), jsonString)
//}
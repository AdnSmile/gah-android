package com.vvwxx.gahandroid.data.remote.request

import com.google.gson.annotations.SerializedName

//data class KamarRequest(
//
//	@field:SerializedName("KamarRequest")
//	val kamarRequest: List<KamarRequestItem>
//)

data class KamarRequestItem(

	@field:SerializedName("jumlah")
	val jumlah: Int,

	@field:SerializedName("id_jenis_kamar")
	val idJenisKamar: Int,

	@field:SerializedName("harga_per_malam")
	val hargaPerMalam: Int
)

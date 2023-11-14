package com.vvwxx.gahandroid.data.remote.response

import com.google.gson.annotations.SerializedName

//data class FasilitasResponse(
//
//	@field:SerializedName("FasilitasResponse")
//	val fasilitasResponse: List<FasilitasResponseItem>
//)

data class FasilitasResponseItem(

	@field:SerializedName("id_layanan")
	val idLayanan: Int,

	@field:SerializedName("satuan")
	val satuan: String,

	@field:SerializedName("tarif_layanan")
	val tarifLayanan: Int,

	@field:SerializedName("nama_layanan")
	val namaLayanan: String
)

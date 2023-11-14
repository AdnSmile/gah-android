package com.vvwxx.gahandroid.data.remote.request

import com.google.gson.annotations.SerializedName

data class FasilitasRequest(

	@field:SerializedName("FasilitasRequest")
	val fasilitasRequest: List<FasilitasRequestItem>
)

data class FasilitasRequestItem(

	@field:SerializedName("id_layanan")
	val idLayanan: Int,

	@field:SerializedName("jumlah")
	val jumlah: Int
)

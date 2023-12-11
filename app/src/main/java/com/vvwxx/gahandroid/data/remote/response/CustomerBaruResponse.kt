package com.vvwxx.gahandroid.data.remote.response

import com.google.gson.annotations.SerializedName

data class CustomerBaruResponse(

	@field:SerializedName("jumlah_customer")
	val jumlahCustomer: Int,

	@field:SerializedName("nama_bulan")
	val namaBulan: String
)

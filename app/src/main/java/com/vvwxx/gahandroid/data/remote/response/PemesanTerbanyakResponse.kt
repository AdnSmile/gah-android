package com.vvwxx.gahandroid.data.remote.response

import com.google.gson.annotations.SerializedName

data class PemesanTerbanyakResponse(

	@field:SerializedName("jumlah_reservasi")
	val jumlahReservasi: Int,

	@field:SerializedName("nama_customer")
	val namaCustomer: String,

	@field:SerializedName("id_customer")
	val idCustomer: Int,

	@field:SerializedName("total_pembayaran")
	val totalPembayaran: String
)

package com.vvwxx.gahandroid.data.remote.response

import com.google.gson.annotations.SerializedName

data class AccountDetailResponse(

	@field:SerializedName("namaInstitusi")
	val namaInstitusi: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("noIdentitas")
	val noIdentitas: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("noTelpon")
	val noTelpon: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("riwayatReservasi")
	val riwayatReservasi: List<RiwayatReservasiItem>,

	@field:SerializedName("alamat")
	val alamat: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class RiwayatReservasiItem(

	@field:SerializedName("idBooking")
	val idBooking: String,

	@field:SerializedName("totalDeposit")
	val totalDeposit: Int,

	@field:SerializedName("idInvoice")
	val idInvoice: Any,

	@field:SerializedName("jumlahAnak")
	val jumlahAnak: Int,

	@field:SerializedName("jumlahDewasa")
	val jumlahDewasa: Int,

	@field:SerializedName("idCustomer")
	val idCustomer: Int,

	@field:SerializedName("buktiBayar")
	val buktiBayar: String,

	@field:SerializedName("tglReservasi")
	val tglReservasi: String,

	@field:SerializedName("tglCheckin")
	val tglCheckin: String,

	@field:SerializedName("totalBayar")
	val totalBayar: Int,

	@field:SerializedName("idFo")
	val idFo: Int,

	@field:SerializedName("tglBayar")
	val tglBayar: String,

	@field:SerializedName("idPic")
	val idPic: Int,

	@field:SerializedName("permintaanKhusus")
	val permintaanKhusus: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("tglCheckout")
	val tglCheckout: String,

	@field:SerializedName("uangJaminan")
	val uangJaminan: Int,

	@field:SerializedName("status")
	val status: String
)

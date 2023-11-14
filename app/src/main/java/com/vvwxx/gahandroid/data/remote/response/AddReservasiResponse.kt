package com.vvwxx.gahandroid.data.remote.response

import com.google.gson.annotations.SerializedName

data class AddReservasiResponse(

	@field:SerializedName("tgl_reservasi")
	val tglReservasi: String,

	@field:SerializedName("jumlah_anak")
	val jumlahAnak: Int,

	@field:SerializedName("uang_jaminan")
	val uangJaminan: Any,

	@field:SerializedName("permintaan_khusus")
	val permintaanKhusus: String,

	@field:SerializedName("id_customer")
	val idCustomer: Int,

	@field:SerializedName("id_invoice")
	val idInvoice: Any,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("total_pembayaran")
	val totalPembayaran: Int,

	@field:SerializedName("id_reservasi")
	val idReservasi: Int,

	@field:SerializedName("tgl_pembayaran")
	val tglPembayaran: Any,

	@field:SerializedName("id_booking")
	val idBooking: String,

	@field:SerializedName("tgl_checkout")
	val tglCheckout: String,

	@field:SerializedName("total_deposit")
	val totalDeposit: Int,

	@field:SerializedName("updated_at")
	val updatedAt: Any,

	@field:SerializedName("jumlah_dewasa")
	val jumlahDewasa: Int,

	@field:SerializedName("id_fo")
	val idFo: Any,

	@field:SerializedName("id_pic")
	val idPic: Int,

	@field:SerializedName("tgl_checkin")
	val tglCheckin: String,

	@field:SerializedName("bukti_pembayaran")
	val buktiPembayaran: Any,

	@field:SerializedName("status")
	val status: String
)

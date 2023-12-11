package com.vvwxx.gahandroid.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailReservasiResponse(

	@field:SerializedName("tgl_reservasi")
	val tglReservasi: String,

	@field:SerializedName("jumlah_anak")
	val jumlahAnak: Int,

	@field:SerializedName("permintaan_khusus")
	val permintaanKhusus: String,

	@field:SerializedName("id_customer")
	val idCustomer: Int,

	@field:SerializedName("f_k_reservasi_in_f_o")
	val fKReservasiInFO: Any,

	@field:SerializedName("id_invoice")
	val idInvoice: Any,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("total_pembayaran")
	val totalPembayaran: Int,

	@field:SerializedName("tgl_pembayaran")
	val tglPembayaran: Any,

	@field:SerializedName("tgl_checkout")
	val tglCheckout: String,

	@field:SerializedName("total_deposit")
	val totalDeposit: Int,

	@field:SerializedName("updated_at")
	val updatedAt: Any,

	@field:SerializedName("f_k_reservasi_in_fasilitas")
	val fKReservasiInFasilitas: List<FKReservasiInFasilitasItem>,

	@field:SerializedName("jumlah_dewasa")
	val jumlahDewasa: Int,

	@field:SerializedName("f_k_reservasi_in_invoice")
	val fKReservasiInInvoice: Any,

	@field:SerializedName("id_fo")
	val idFo: Any,

	@field:SerializedName("tgl_checkin")
	val tglCheckin: String,

	@field:SerializedName("bukti_pembayaran")
	val buktiPembayaran: Any,

	@field:SerializedName("uang_jaminan")
	val uangJaminan: Any,

	@field:SerializedName("id_reservasi")
	val idReservasi: Int,

	@field:SerializedName("id_booking")
	val idBooking: String,

	@field:SerializedName("f_k_reservasi_in_p_i_c")
	val fKReservasiInPIC: Any,

	@field:SerializedName("f_k_reservasi_in_transaksi_kamar")
	val fKReservasiInTransaksiKamar: List<FKReservasiInTransaksiKamarItem>,

	@field:SerializedName("f_k_reservasi_in_customer")
	val fKReservasiInCustomer: FKReservasiInCustomer,

	@field:SerializedName("id_pic")
	val idPic: Any,

	@field:SerializedName("status")
	val status: String
)

data class FKTransaksiKamarInJenisKamar(

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("id_jenis_kamar")
	val idJenisKamar: Int
)

data class FKReservasiInTransaksiKamarItem(

	@field:SerializedName("id_transaksi_kamar")
	val idTransaksiKamar: Int,

	@field:SerializedName("f_k_transaksi_kamar_in_jenis_kamar")
	val fKTransaksiKamarInJenisKamar: FKTransaksiKamarInJenisKamar,

	@field:SerializedName("id_jenis_kamar")
	val idJenisKamar: Int,

	@field:SerializedName("harga_per_malam")
	val hargaPerMalam: Int,

	@field:SerializedName("id_kamar")
	val idKamar: Any,

	@field:SerializedName("tipe_bed")
	val tipeBed: Any,

	@field:SerializedName("id_reservasi")
	val idReservasi: Int
)

data class FKTransaksiFasilitasInFasilitas(

	@field:SerializedName("id_layanan")
	val idLayanan: Int,

	@field:SerializedName("satuan")
	val satuan: String,

	@field:SerializedName("tarif_layanan")
	val tarifLayanan: Int,

	@field:SerializedName("nama_layanan")
	val namaLayanan: String
)

data class FKReservasiInFasilitasItem(

	@field:SerializedName("id_layanan")
	val idLayanan: Int,

	@field:SerializedName("tgl_menerima")
	val tglMenerima: String,

	@field:SerializedName("f_k_transaksi_fasilitas_in_fasilitas")
	val fKTransaksiFasilitasInFasilitas: FKTransaksiFasilitasInFasilitas,

	@field:SerializedName("jumlah")
	val jumlah: Int,

	@field:SerializedName("sub_total")
	val subTotal: Int,

	@field:SerializedName("id_transaksi_layanan")
	val idTransaksiLayanan: Int,

	@field:SerializedName("id_reservasi")
	val idReservasi: Int
)

data class FKReservasiInCustomer(

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("institusi")
	val institusi: Any,

	@field:SerializedName("id_customer")
	val idCustomer: Int,

	@field:SerializedName("no_identitas")
	val noIdentitas: String,

	@field:SerializedName("no_telpon")
	val noTelpon: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("alamat")
	val alamat: String
)

package com.vvwxx.gahandroid.data.remote.response

import com.google.gson.annotations.SerializedName

//data class AvailabilityKamarResponse(
//
//	@field:SerializedName("AvailabilityKamarResponse")
//	val availabilityKamarResponse: List<AvailabilityKamarResponseItem>
//)

data class FKKamarInJenisKamar(

	@field:SerializedName("rincian_kamar")
	val rincianKamar: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("ukuran_kamar")
	val ukuranKamar: Int,

	@field:SerializedName("id_jenis_kamar")
	val idJenisKamar: Int,

	@field:SerializedName("tarif_dasar")
	val tarifDasar: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("kapasitas")
	val kapasitas: Int,

	@field:SerializedName("tipe_bed")
	val tipeBed: String
)

data class AvailabilityKamarResponse(

	@field:SerializedName("nama_season")
	val namaSeason: String? = null,

	@field:SerializedName("f_k_kamar_in_jenis_kamar")
	val fKKamarInJenisKamar: FKKamarInJenisKamar,

	@field:SerializedName("id_jenis_kamar")
	val idJenisKamar: Int,

	@field:SerializedName("jumlah_kamar")
	val jumlahKamar: Int,

	@field:SerializedName("tarif_dasar")
	val tarifDasar: Int? = null,

	@field:SerializedName("harga_terbaru")
	val hargaTerbaru: Int,

	@field:SerializedName("jenis_kamar")
	val jenisKamar: String,

	@field:SerializedName("tarif_harga")
	val tarifHarga: Int,

	@field:SerializedName("jenis_season")
	val jenisSeason: String
)

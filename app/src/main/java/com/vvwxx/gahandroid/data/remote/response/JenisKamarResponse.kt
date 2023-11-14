package com.vvwxx.gahandroid.data.remote.response

import com.google.gson.annotations.SerializedName

//data class JenisKamarResponse(
//
//	@field:SerializedName("JenisKamarResponse")
//	val jenisKamarResponse: List<JenisKamarResponseItem>
//)

data class JenisKamarResponseItem(

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

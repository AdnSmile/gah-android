package com.vvwxx.gahandroid.data.model

import com.vvwxx.gahandroid.R

data class JenisKamar(
    val id: Int,
    val image: Int,
    val nama: String,
    val deskripsi: String,
    val kapasitas: Int,
    val rincianKamar: String,
    val tipeBed: String,
    val ukuranKamar: Int,
    val tarif: Long
)

val dummyJenisKamar = listOf(
    JenisKamar(
        id = 1,
        image = R.drawable.superior_room,
        nama = "Superior",
        deskripsi = "Internet - WiFi Gratis\n" +
                "Hiburan - Televisi LCD dengan channel TV premium channels\n" +
                "Makan Minum - Pembuat kopi/teh, minibar, layanan kamar 24-jam, air minum kemasan gratis, termasuk sarapan\n" +
                "Untuk tidur - Seprai kualitas premium dan gorden",
        kapasitas = 2,
        rincianKamar = "AC, Air minum kemasan gratis, Brankas dalam kamar (ukuran laptop), Fasilitas membuat kopi/teh, Jubah mandi, Layanan kamar (24 jam), Meja tulis, Minibar, Pembersihan kamar harian, Pengering rambut, Peralatan mandi gratis, Sandal, Telepon, Tempat tidur eks",
        tipeBed = "1 Double atau 2 Twin",
        ukuranKamar = 22,
        tarif = 300000
    ),

    JenisKamar(
        id = 3,
        image = R.drawable.executive_room,
        nama = "Executive Deluxe",
        deskripsi = "Layout - ruang duduk terpisah\n" +
                "Internet - WiFi Gratis\n" +
                "Hiburan - Televisi LCD dengan channel TV premium channels\n" +
                "Makan Minum - Pembuat kopi/teh, minibar, layanan kamar 24-jam, air minum kemasan gratis, termasuk sarapan\n" +
                "Untuk tidur - Seprai kualitas pr",
        kapasitas = 2,
        rincianKamar = "AC, Air minum kemasan gratis, Brankas dalam kamar (ukuran laptop), Fasilitas membuat kopi/teh, Jubah mandi, Layanan kamar (24 jam), Meja tulis, Minibar, Pembersihan kamar harian, Pengering rambut, Peralatan mandi gratis, Sandal, Telepon, Tempat tidur eks",
        tipeBed = "1 king",
        ukuranKamar = 22,
        tarif = 600000
    ),

    JenisKamar(
        id = 4,
        image = R.drawable.junior_room,
        nama = "Junior Suite",
        deskripsi = "Layout - ruang duduk terpisah\n" +
                "Internet - WiFi Gratis\n" +
                "Hiburan - Televisi LCD dengan channel TV premium channels\n" +
                "Makan Minum - Pembuat kopi/teh, minibar, layanan kamar 24-jam, air minum kemasan gratis, termasuk sarapan\n" +
                "Untuk tidur - Seprai kualitas pr",
        kapasitas = 2,
        rincianKamar = "AC, Air minum kemasan gratis, Brankas dalam kamar (ukuran laptop), Fasilitas membuat kopi/teh, Jubah mandi, Layanan kamar (24 jam), Meja tulis, Minibar, Pembersihan kamar harian, Pengering rambut, Peralatan mandi gratis, Sandal, Telepon, Tempat tidur eks",
        tipeBed = "1 king",
        ukuranKamar = 22,
        tarif = 800000
    ),

    JenisKamar(
        id = 2,
        image = R.drawable.deluxe_room,
        nama = "Double Deluxe",
        deskripsi = "Layout - ruang duduk terpisah\n" +
                "Internet - WiFi Gratis\n" +
                "Hiburan - Televisi LCD dengan channel TV premium channels\n" +
                "Makan Minum - Pembuat kopi/teh, minibar, layanan kamar 24-jam, air minum kemasan gratis, termasuk sarapan\n" +
                "Untuk tidur - Seprai kualitas pr",
        kapasitas = 2,
        rincianKamar = "AC, Air minum kemasan gratis, Brankas dalam kamar (ukuran laptop), Fasilitas membuat kopi/teh, Jubah mandi, Layanan kamar (24 jam), Meja tulis, Minibar, Pembersihan kamar harian, Pengering rambut, Peralatan mandi gratis, Sandal, Telepon, Tempat tidur eks",
        tipeBed = "1 king atau 2 double bed",
        ukuranKamar = 22,
        tarif = 1500000
    )

)

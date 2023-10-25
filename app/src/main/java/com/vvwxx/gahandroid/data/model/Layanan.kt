package com.vvwxx.gahandroid.data.model

import com.vvwxx.gahandroid.R

data class Layanan(
    val id: Int,
    val image: Int,
    val nama: String,
    val satuan: String,
    val tarif: Long
)

var dummyLayanan = listOf(

    Layanan(
        id = 1,
        nama = "Extra Bed",
        satuan = "per bed",
        tarif = 200000,
        image = R.drawable.extra_bed,
    ),

    Layanan(
        id = 2,
        nama = "Laundry",
        satuan = "per baju(flat)",
        tarif = 50000,
        image = R.drawable.laundry,
    ),

    Layanan(
        id = 3,
        nama = "Massage",
        satuan = "per orang",
        tarif = 60000,
        image = R.drawable.massage,
    ),

    Layanan(
        id = 4,
        nama = "Meeting room",
        satuan = "per jam",
        tarif = 70000,
        image = R.drawable.meeting_room,
    ),

    Layanan(
        id = 5,
        nama = "Tambahan breakfast",
        satuan = "per pax",
        tarif = 100000,
        image = R.drawable.breakfast,
    ),
)
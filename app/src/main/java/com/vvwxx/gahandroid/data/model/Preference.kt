package com.vvwxx.gahandroid.data.model

data class Preference(

    val id: Int,
    val token: String,
    val isLogin: Boolean,
    val role: String,
    val idCustomer: Int,
    val hargaTerbaruKamar: Int,
    val dewasa: Int,
    val anak: Int,
    val checkin: String,
    val checkout: String
)

package com.vvwxx.gahandroid.ui.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object History : Screen("history")

    object Login : Screen("login")

    object Register : Screen("register")

    object Setting : Screen("setting")

    object Profile : Screen("profile")

    object Reservasi : Screen("reservasi")

    object Booking : Screen("booking/{id}") {
        fun createRoute(id: Int) = "booking/$id"
    }

    object Resume : Screen("resume/{id}") {
        fun createRoute(id: Int) = "resume/$id"
    }

    object DetailJenisKamar : Screen("home/{id}") {
        fun createRoute(id: Int) = "home/$id"
    }

    object DetailKetersediaanKamar : Screen("kamar_tersedia/{id}") {
        fun createRoute(id: Int) = "kamar_tersedia/$id"
    }
}

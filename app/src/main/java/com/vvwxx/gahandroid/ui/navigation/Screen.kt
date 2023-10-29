package com.vvwxx.gahandroid.ui.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object History : Screen("history")

    object Profile : Screen("profile")

    object Login : Screen("login")

    object Register : Screen("register")

    object DetailJenisKamar : Screen("home/{id}") {
        fun createRoute(id: Int) = "home/$id"
    }
}

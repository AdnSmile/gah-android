package com.vvwxx.gahandroid.ui.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object History : Screen("history")

    object Login : Screen("login")

    object Register : Screen("register")

    object Setting : Screen("setting")

    object Profile : Screen("profile")

    object DetailJenisKamar : Screen("home/{id}") {
        fun createRoute(id: Int) = "home/$id"
    }
}

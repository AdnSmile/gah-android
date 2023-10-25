package com.vvwxx.gahandroid.ui.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object History : Screen("history")

    object Profile : Screen("profile")

}

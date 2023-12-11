package com.vvwxx.gahandroid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vvwxx.gahandroid.ui.navigation.NavigationItem
import com.vvwxx.gahandroid.ui.navigation.Screen
import com.vvwxx.gahandroid.ui.screen.booking.BookingScreen
import com.vvwxx.gahandroid.ui.screen.detail.DetailJenisScreen
import com.vvwxx.gahandroid.ui.screen.history.HistoryScreen
import com.vvwxx.gahandroid.ui.screen.home.HomeScreen
import com.vvwxx.gahandroid.ui.screen.laporan.CustomerBaruScreen
import com.vvwxx.gahandroid.ui.screen.laporan.PemesanTerbanyakScreen
import com.vvwxx.gahandroid.ui.screen.login.LoginScreen
import com.vvwxx.gahandroid.ui.screen.profile.ProfileScreen
import com.vvwxx.gahandroid.ui.screen.register.RegisterScreen
import com.vvwxx.gahandroid.ui.screen.reservasi.DetailKetersediaanScreen
import com.vvwxx.gahandroid.ui.screen.reservasi.ReservasiScreen
import com.vvwxx.gahandroid.ui.screen.resume.ResumeScreen
import com.vvwxx.gahandroid.ui.screen.setting.SettingScreen

@Composable
fun HotelApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailJenisKamar.route
                && currentRoute != Screen.Login.route
                && currentRoute != Screen.Register.route
                && currentRoute != Screen.CustomerBaru.route
                && currentRoute != Screen.PemesanTerbanyak.route){
                BottomBar(navController)
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.Home.route) {
                // home screen

                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.DetailJenisKamar.createRoute(id))
                    },
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
                )
            }

            composable(Screen.Reservasi.route) {

                ReservasiScreen(
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
                    navigateToLogin = { navController.navigate(Screen.Login.route) }) { id ->

                    navController.navigate(Screen.DetailKetersediaanKamar.createRoute(id))
                }

            }

            composable(
                route = Screen.Booking.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("id") ?: 0
                BookingScreen(
                    id = id,
                    navigateBack = { navController.navigateUp() },
                    navigateToResume = {idBook ->
                        navController.navigate(Screen.Resume.createRoute(idBook))
                    }
                )
            }

            composable(Screen.History.route) {
                // history screen

                HistoryScreen(
                    navigateToLogin = { navController.navigate(Screen.Login.route) }
                )
            }

            composable(Screen.Profile.route) {
                // Profile screen

                ProfileScreen(
                    navigateBack = { navController.navigateUp() }
                )
            }

            composable(Screen.CustomerBaru.route) {
                // Customer Baru screen

                CustomerBaruScreen(
                    navigateBack = { navController.navigateUp() }
                )
            }

            composable(Screen.PemesanTerbanyak.route) {
                // Pemesan Terbanyak screen

                PemesanTerbanyakScreen(
                    navigateBack = { navController.navigateUp() }
                )
            }

            composable(Screen.Setting.route) {
                // setting screen

                SettingScreen(
                    navigateToLogin = { navController.navigate(Screen.Login.route) },
                    navigateToHome = { navController.navigate(Screen.Home.route)},
                    navigateTolProfile = {navController.navigate(Screen.Profile.route)},
                    navigateToCustomerBaru = {navController.navigate(Screen.CustomerBaru.route)},
                    navigateToPemesanTerbanyak = {navController.navigate(Screen.PemesanTerbanyak.route)}
                )
            }

            composable(Screen.Login.route) {
                // Login screen

                LoginScreen(
                    navigateBack = { navController.navigateUp() },
                    navigateToRegister = { navController.navigate(Screen.Register.route) },
                    navigateToHome = {navController.navigate(Screen.Home.route)}
                )
            }

            composable(Screen.Register.route) {
                // Register screen

                RegisterScreen {
                    navController.navigateUp()
                }
            }

            composable(
                route = Screen.DetailJenisKamar.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("id") ?: 0
                DetailJenisScreen(
                    id = id,
                    navigateBack = { navController.navigateUp() },
                )
            }

            composable(
                route = Screen.DetailKetersediaanKamar.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType}),
            ) {
                val id = it.arguments?.getInt("id") ?: 0
                DetailKetersediaanScreen(
                    id = id,
                    navigateBack = {navController.navigateUp()},
                    navigateToBooking = {idBook ->

                        navController.navigate(Screen.Booking.createRoute(idBook))
                    }
                )
            }

            composable(
                route = Screen.Resume.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType})
            ) {
                val id = it.arguments?.getInt("id") ?: 0
                ResumeScreen(
                    id = id,
                    navigateBack = {navController.navigateUp()}
                )
            }

        }

    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_reservasi),
                icon = Icons.Default.CalendarToday,
                screen = Screen.Reservasi
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_history),
                icon = Icons.Default.History,
                screen = Screen.History
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_setting),
                icon = Icons.Default.Settings,
                screen = Screen.Setting
            ),
        )

        navigationItems.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) }
            )
        }
    }

}

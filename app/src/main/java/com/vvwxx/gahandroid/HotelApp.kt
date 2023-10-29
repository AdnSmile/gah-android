package com.vvwxx.gahandroid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.vvwxx.gahandroid.ui.screen.detail.DetailJenisScreen
import com.vvwxx.gahandroid.ui.screen.history.HistoryScreen
import com.vvwxx.gahandroid.ui.screen.home.HomeScreen
import com.vvwxx.gahandroid.ui.screen.login.LoginScreen

@OptIn(ExperimentalMaterial3Api::class)
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
                && currentRoute != Screen.Register.route){
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

            composable(Screen.History.route) {
                // history screen

                HistoryScreen(
                    navigateToLogin = { navController.navigate(Screen.Login.route) }
                )
            }

            composable(Screen.Profile.route) {
                // profile screen
            }

            composable(Screen.Login.route) {
                // Login screen

                LoginScreen(
                    navigateBack = { navController.navigateUp() }
                )
            }

            composable(Screen.Register.route) {
                // Register screen
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
                title = stringResource(id = R.string.menu_history),
                icon = Icons.Default.History,
                screen = Screen.History
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_profile),
                icon = Icons.Default.Person,
                screen = Screen.Profile
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

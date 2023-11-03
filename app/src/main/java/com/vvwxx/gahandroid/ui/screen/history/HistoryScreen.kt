package com.vvwxx.gahandroid.ui.screen.history

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vvwxx.gahandroid.di.Injection
import com.vvwxx.gahandroid.ui.ViewModelFactory
import com.vvwxx.gahandroid.ui.components.NotLoginScreen

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: HistoryViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navigateToLogin: () -> Unit,
) {

    val preferenceState by viewModel.getAccountPref.collectAsState(initial = null)

    var isLogin by rememberSaveable {
        mutableStateOf(false)
    }

    var token by rememberSaveable {
        mutableStateOf("")
    }

    LaunchedEffect(preferenceState) {
        val preference = preferenceState
        if (preference != null) {
            isLogin = preference.isLogin
            token = preference.token
        }
    }

    if (!isLogin) NotLoginScreen(navigateToLogin = navigateToLogin)
    else {
        DataEmpty()
    }

//    viewModel.getAccountPref.collectAsState(initial = UiState.Loading).value.let { uiState ->
//
//        when (uiState) {
//            is UiState.Loading -> {
//
//            }
//            is UiState.Success<*> -> {
//               val isLogin = uiState.data.
//            }
//            is UiState.Error -> {}
//        }
//
//    }
}

@Composable
fun DataEmpty() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            Text(text = "Sudah login, tapi datanya kosong")
        }
    }

}
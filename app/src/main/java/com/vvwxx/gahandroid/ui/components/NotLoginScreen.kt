package com.vvwxx.gahandroid.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotLoginScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {

            Text(text = "Anda belum login, login dulu ya")

            FilledTonalButton(
                onClick = { navigateToLogin() },
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(0.6f)
            ) {
                Text(text = "Login")
            }
        }
    }
}
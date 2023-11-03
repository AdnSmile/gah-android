package com.vvwxx.gahandroid.ui.screen.login

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vvwxx.gahandroid.data.model.Preference
import com.vvwxx.gahandroid.di.Injection
import com.vvwxx.gahandroid.ui.ViewModelFactory
import com.vvwxx.gahandroid.ui.common.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToRegister: () -> Unit,
    context: Context = LocalContext.current,
    viewModel: LoginViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navigateToHome: () -> Unit
) {

    var username by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    var passwordHidden by rememberSaveable {
        mutableStateOf(true)
    }

    var loading by rememberSaveable {
        mutableStateOf(false)
    }

    val loginState = viewModel.loginSuccess.collectAsState().value

    LaunchedEffect(key1 = loginState.res, block = {
        if (loginState.res != null) {
            navigateToHome()
        }
    })

    viewModel.loginResponse.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
            }

            is UiState.Success -> {
                loading = false
                viewModel.saveAccountPref(
                    Preference(
                        id = uiState.data.data.account.idAccount,
                        token = uiState.data.data.token,
                        isLogin = true,
                        role = uiState.data.data.account.role
                    )
                )
            }

            is UiState.Error -> {
                loading = false
            }
        }
    }

    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = null,
        modifier = Modifier
            .clickable { navigateBack() }
            .padding(18.dp)
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Halo, selamat datang",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                )


                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username")},
                    placeholder = { Text(text = "test123") },
                    shape = RoundedCornerShape(24.dp),
                    supportingText = { Text("Masukan username anda!") },
                    leadingIcon = {
                        Icon(Icons.Filled.Person, contentDescription = null)
                    },
                    modifier = Modifier
                        .padding(16.dp)
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    singleLine = true,
                    visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        IconButton(onClick = { passwordHidden = !passwordHidden }) {
                            val visibilityIcon =
                                if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            // Please provide localized description for accessibility services
                            val description = if (passwordHidden) "Show password" else "Hide password"
                            Icon(imageVector = visibilityIcon, contentDescription = description)
                        }
                    },
                    label = { Text("Password")},
                    placeholder = { Text(text = "******") },
                    shape = RoundedCornerShape(24.dp),
                    supportingText = { Text("Masukan password anda!") },
                    leadingIcon = {
                        Icon(Icons.Filled.Lock, contentDescription = null)
                    },
                    modifier = Modifier
                        .padding(16.dp)
                )

                FilledTonalButton(
                    onClick = {
                        viewModel.loginAccount(username, password)
                        loading = true
                    },
                    enabled = !loading,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(0.6f)
                ) {
                    if (!loading) Text(text = "Masuk")
                    else {
                        CircularProgressIndicator(
                            modifier = Modifier.width(24.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                        )
                    }
                }

                Row(modifier = Modifier.padding(bottom = 24.dp)) {

                    Text(
                        text = "Tidak punya akun?",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Normal
                        )
                    )

                Spacer(modifier = Modifier.padding(2.dp))

                    ClickableText(
                        text = AnnotatedString("Registrasi"),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Normal,
                            color = Color.Blue
                        ),
                        onClick = { navigateToRegister() },
                    )
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    GahandroidTheme {
//        LoginScreen()
//    }
//}
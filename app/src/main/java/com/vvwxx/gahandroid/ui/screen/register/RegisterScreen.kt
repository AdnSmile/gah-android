package com.vvwxx.gahandroid.ui.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vvwxx.gahandroid.ui.theme.GahandroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onClick : () -> Unit
) {

    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var fullname by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var noIdentitas by rememberSaveable { mutableStateOf("") }
    var noTelpon by rememberSaveable { mutableStateOf("") }
    var alamat by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable {
        mutableStateOf(true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        contentAlignment = Alignment.Center
    ) {

        Column {

            Text(
                modifier = Modifier.padding(8.dp, bottom = 28.dp),
                text = "Registrasi",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
            )

            Row {
                OutlinedTextField(
                    value = fullname,
                    onValueChange = { fullname = it },
                    label = { Text("Nama")},
                    singleLine = true,
                    placeholder = { Text(text = "Catlyn") },
                    shape = RoundedCornerShape(24.dp),
                    leadingIcon = {
                        Icon(Icons.Filled.Abc, contentDescription = null)
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                )

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username")},
                    singleLine = true,
                    placeholder = { Text(text = "test12") },
                    shape = RoundedCornerShape(24.dp),
                    leadingIcon = {
                        Icon(Icons.Filled.Person, contentDescription = null)
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                )
            }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                singleLine = true,
                label = { Text("Email")},
                placeholder = { Text(text = "example@mail.com") },
                shape = RoundedCornerShape(24.dp),
                leadingIcon = {
                    Icon(Icons.Filled.Email, contentDescription = null)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
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
                leadingIcon = {
                    Icon(Icons.Filled.Lock, contentDescription = null)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            // no telpon
            OutlinedTextField(
                value = noTelpon,
                onValueChange = { noTelpon = it },
                singleLine = true,
                label = { Text("Nomor Telpon")},
                placeholder = { Text(text = "08345354") },
                shape = RoundedCornerShape(24.dp),
                leadingIcon = {
                    Icon(Icons.Filled.Phone, contentDescription = null)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            // no identitas
            OutlinedTextField(
                value = noIdentitas,
                onValueChange = { noIdentitas = it },
                singleLine = true,
                label = { Text("Nomor Identitas")},
                placeholder = { Text(text = "234234234") },
                shape = RoundedCornerShape(24.dp),
                leadingIcon = {
                    Icon(Icons.Filled.Assignment, contentDescription = null)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            // alamat
            OutlinedTextField(
                value = alamat,
                onValueChange = { alamat = it },
                singleLine = true,
                label = { Text("Alamat")},
                placeholder = { Text(text = "Jl. Baru") },
                shape = RoundedCornerShape(24.dp),
                leadingIcon = {
                    Icon(Icons.Filled.Home, contentDescription = null)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Daftar button
                FilledTonalButton(
                    onClick = onClick,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(0.7f)
                ) {
                    Text(text = "Daftar")
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    GahandroidTheme {
        RegisterScreen{}
    }
}
package com.vvwxx.gahandroid.ui.screen.reservasi

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vvwxx.gahandroid.data.remote.response.AvailabilityKamarResponse
import com.vvwxx.gahandroid.di.Injection
import com.vvwxx.gahandroid.ui.ViewModelFactory
import com.vvwxx.gahandroid.ui.common.UiState
import com.vvwxx.gahandroid.ui.components.AvailabilityKamarItem
import com.vvwxx.gahandroid.ui.components.NotLoginScreen
import com.vvwxx.gahandroid.util.DateTransformation
import com.vvwxx.gahandroid.util.convertDateFormat
import com.vvwxx.gahandroid.util.showToast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservasiScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: ReservasiViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navigateToLogin: () -> Unit,
    navigateToDetail: (Int) -> Unit,
) {

    val preferenceState by viewModel.getAccountPref.collectAsState(initial = null)

    var checkin by rememberSaveable { mutableStateOf("") }
    var checkout by rememberSaveable { mutableStateOf("") }
    var dewasa by rememberSaveable { mutableStateOf("") }
    var anak by rememberSaveable { mutableStateOf("") }

    var token by rememberSaveable { mutableStateOf("") }

    var isLogin by rememberSaveable {
        mutableStateOf(false)
    }

    var isOpen by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(preferenceState) {
        val preference = preferenceState
        if (preference != null) {
            isLogin = preference.isLogin
            token = preference.token
        }
    }



    val maxChar = 8

    viewModel.ketersediaanKamarResponse.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {}

            is UiState.Success -> {
//                isOpen = true
                if (isOpen) {
                    if (!isLogin) NotLoginScreen(navigateToLogin = navigateToLogin)
                    else {
                        KetersediianContent(
                            jenisKamar = uiState.data.data,
                            isOpen = isOpen,
                            onOpenChange = { newIsOpen -> isOpen = newIsOpen },
                            navigateToDetail = navigateToDetail
                        )
                    }

                }

            }

            is UiState.Error -> {
                showToast(context, uiState.errorMessage)
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
    ) {

        if (!isOpen) {
            Text(
                text = "Cari Ketersediaan Kamar",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
            )

            OutlinedTextField(
                value = checkin,
                onValueChange = {
                    if (it.length <= maxChar) checkin = it
                },
                label = { Text("Tanggal Check In") },
                placeholder = { Text(text = "dd/mm/yyyy") },
                shape = RoundedCornerShape(24.dp),
                singleLine =  true,
                leadingIcon = {
                    Icon(Icons.Filled.CalendarMonth, contentDescription = null)
                },
                visualTransformation = DateTransformation(),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = checkout,
                onValueChange = {
                    if (it.length <= maxChar) checkout = it
                },
                label = { Text("Tanggal Check Out") },
                placeholder = { Text(text = "dd/mm/yyyy") },
                shape = RoundedCornerShape(24.dp),
                singleLine =  true,
                leadingIcon = {
                    Icon(Icons.Filled.CalendarMonth, contentDescription = null)
                },
                visualTransformation = DateTransformation(),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = dewasa,
                onValueChange = {dewasa = it},
                label = { Text("Jumlah Dewasa") },
                shape = RoundedCornerShape(24.dp),
                singleLine =  true,
                leadingIcon = {
                    Icon(Icons.Filled.PersonOutline, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = anak,
                onValueChange = {anak = it},
                label = { Text("Jumlah Anak") },
                shape = RoundedCornerShape(24.dp),

                singleLine =  true,
                leadingIcon = {
                    Icon(Icons.Filled.ChildCare, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                FilledTonalButton(
                    onClick = {
                        isOpen = true
                        viewModel.getKetersediaanKamar(
                            token, convertDateFormat(checkin),
                            convertDateFormat(checkout),
                            dewasa.toInt(), anak.toInt()
                        )
                        viewModel.setReservasiPref(dewasa.toInt(), anak.toInt(), checkin, checkout)
                        checkin = ""
                        checkout = ""
                        dewasa = ""
                        anak = ""
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(0.7f)
                ) {
                    Text(text = "Cari")
                }
            }
        }


    }
}

@Composable
fun KetersediianContent(
    jenisKamar: List<AvailabilityKamarResponse>,
    isOpen: Boolean,
    onOpenChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: ReservasiViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navigateToDetail: (Int) -> Unit
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 16.dp, top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.testTag("JenisKamarList")
        ) {

            item {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            onOpenChange(!isOpen)
                            showToast(context, isOpen.toString())
                        }
                        .padding(18.dp)
                )
            }

            items(jenisKamar, key = {it.idJenisKamar}) { data ->
                AvailabilityKamarItem(
                    jenisKamar = data,
                    modifier = Modifier.clickable {
                        viewModel.setHargaTerbaru(data.hargaTerbaru)
                        navigateToDetail(data.idJenisKamar)
                    }
                )
            }

        }
    }
}

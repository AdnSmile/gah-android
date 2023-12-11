package com.vvwxx.gahandroid.ui.screen.laporan

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vvwxx.gahandroid.data.remote.response.PemesanTerbanyakResponse
import com.vvwxx.gahandroid.di.Injection
import com.vvwxx.gahandroid.ui.ViewModelFactory
import com.vvwxx.gahandroid.ui.common.UiState
import com.vvwxx.gahandroid.ui.components.ReservasiTerbanyakItem

@Composable
fun PemesanTerbanyakScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: LaporanViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navigateBack: () -> Unit,
) {

    val preferenceState by viewModel.getAccountPref.collectAsState(initial = null)
    var token by rememberSaveable { mutableStateOf("") }
    var tahun by rememberSaveable { mutableIntStateOf(2023) }

    LaunchedEffect(preferenceState) {
        val preference = preferenceState
        if (preference != null) {
            token = preference.token
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {

        Row(Modifier.padding(top = 8.dp)) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.weight(0.1f)
                    .clickable { navigateBack() }
            )

            Text(
                text = "5 RESERVASI TERBANYAK",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Normal
                ),
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 20.dp)
                    .weight(0.9f)
            )
        }

        Row(Modifier.padding(top = 16.dp)) {
            OutlinedTextField(
                value = tahun.toString(),
                onValueChange = { tahun = it.toInt() },
                label = { Text("Tahun") },
                shape = RoundedCornerShape(24.dp),
                leadingIcon = {
                    Icon(Icons.Filled.CalendarToday, contentDescription = null)
                },
                modifier = Modifier.padding(top = 12.dp)
                    .weight(0.7f)
            )

            FilledTonalButton(
                onClick = { viewModel.getPemesanTerbanyak(token, tahun) },
                modifier = Modifier.padding(start = 12.dp, top = 24.dp)
            ) {
                Text(text = "Tampil")
            }
        }

        viewModel.listPemesanTerbanyak.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {

                }

                is UiState.Success -> {
                    PemesanTerbanyakContent(
                        pemesanan = uiState.data.data,
                    )
                }

                is UiState.Error -> {}
            }
        }

    }
}

@Composable
fun PemesanTerbanyakContent(
    modifier: Modifier = Modifier,
    pemesanan: List<PemesanTerbanyakResponse>,
) {

    Column(
        modifier = modifier.fillMaxWidth().padding(top = 4.dp, bottom = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.testTag("JenisKamarList")
        ) {
            items(pemesanan, key = {it.idCustomer}) {
                ReservasiTerbanyakItem(
                    nama = it.namaCustomer,
                    jumlah = it.jumlahReservasi,
                    total = it.totalPembayaran.toInt(),
                )
            }
        }
    }
}

package com.vvwxx.gahandroid.ui.screen.resume

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vvwxx.gahandroid.di.Injection
import com.vvwxx.gahandroid.ui.ViewModelFactory
import com.vvwxx.gahandroid.ui.common.UiState
import com.vvwxx.gahandroid.ui.theme.GahandroidTheme
import com.vvwxx.gahandroid.util.showToast

@Composable
fun ResumeScreen(
    id: Int,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: ResumeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
) {

    val preferenceState by viewModel.getAccountPref.collectAsState(initial = null)

    var idBooking by rememberSaveable { mutableStateOf("") }
    var totalBayar by rememberSaveable { mutableStateOf("") }

    var namaKamar by rememberSaveable { mutableStateOf("") }
    var hargaPermalam by rememberSaveable { mutableStateOf("") }

    var namaFasilitas by rememberSaveable { mutableStateOf("") }
    var jumlahFasilitas by rememberSaveable { mutableStateOf("") }
    var subTotalFasilitas by rememberSaveable { mutableStateOf("") }

    var token by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(preferenceState) {
        val preference = preferenceState
        if (preference != null) {
            token = preference.token
        }
    }

    viewModel.detailReservasiResponse.collectAsState(initial = UiState.Loading).value.let { uiState ->

        when (uiState) {
            is UiState.Loading -> {
                viewModel.getDetailReservasi(token, id)
            }
            is UiState.Success -> {
                idBooking = uiState.data.data.idBooking
                totalBayar = uiState.data.data.totalPembayaran.toString()

                namaKamar = uiState.data.data.fKReservasiInTransaksiKamar[0].fKTransaksiKamarInJenisKamar.nama
                hargaPermalam = uiState.data.data.fKReservasiInTransaksiKamar[0].hargaPerMalam.toString()

                namaFasilitas = uiState.data.data.fKReservasiInFasilitas[0].fKTransaksiFasilitasInFasilitas.namaLayanan
                jumlahFasilitas = uiState.data.data.fKReservasiInFasilitas[0].jumlah.toString()
                subTotalFasilitas = uiState.data.data.fKReservasiInFasilitas[0].subTotal.toString()
            }
            is UiState.Error -> {
                showToast(context, uiState.errorMessage)
                Log.d("error", uiState.errorMessage)
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .clickable { navigateBack() }
        )

        Text(
            text = "Resume Reservasi",
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "ID TRANSAKSI: $idBooking",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )

        Text(
            text = "Kamar yang dipesan: \n\tKamar: $namaKamar \n\tHarga: $hargaPermalam",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )

        Text(
            text = "Fasilitas yang dipilih: \n\tNama: $namaFasilitas \n\tJumlah: $jumlahFasilitas \n\tSubtotal: $subTotalFasilitas",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )

        Text(
            text = "Total Pembayaran: $totalBayar",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )

        Text(
            text = "Bayar pada bank: Bank Diamond \nAtas nama: PT Atma Jaya \nNomor Rekening : 770011770022",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilledTonalButton(
                onClick = {  },
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(0.6f)
            ) {
                Text(text = "Bayar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResumeScreenPrev() {
    GahandroidTheme {
        ResumeScreen(id = 62, navigateBack = {})
    }
}
package com.vvwxx.gahandroid.ui.screen.booking

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MeetingRoom
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vvwxx.gahandroid.data.remote.request.FasilitasRequestItem
import com.vvwxx.gahandroid.data.remote.request.KamarRequestItem
import com.vvwxx.gahandroid.data.remote.response.FasilitasResponseItem
import com.vvwxx.gahandroid.data.remote.response.JenisKamarResponseItem
import com.vvwxx.gahandroid.di.Injection
import com.vvwxx.gahandroid.ui.ViewModelFactory
import com.vvwxx.gahandroid.ui.common.UiState
import com.vvwxx.gahandroid.util.convertDateFormat

@Composable
fun BookingScreen(
    id: Int,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: BookingViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navigateBack: () -> Unit,
    navigateToResume: (Int) -> Unit,
) {

    val preferenceState by viewModel.getAccountPref.collectAsState(initial = null)

    var hargaBaru by rememberSaveable {mutableIntStateOf(0) }
    var dewasa by rememberSaveable { mutableStateOf("") }
    var anak by rememberSaveable { mutableStateOf("") }
    var checkin by rememberSaveable { mutableStateOf("") }
    var checkout by rememberSaveable { mutableStateOf("") }
    var namaJenisKamar by rememberSaveable { mutableStateOf("") }
    var fasilitas: List<FasilitasResponseItem> = emptyList()
    var mExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    var token by rememberSaveable { mutableStateOf("") }
    var idFasilitas by remember { mutableStateOf("") }
    var idCustomer by remember { mutableStateOf(0) }
    var idResrvasi by rememberSaveable { mutableStateOf(0) }

    val reservasiState = viewModel.addReservasiSuccess.collectAsState().value

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    LaunchedEffect(preferenceState) {
        val preference = preferenceState
        if (preference != null) {
            hargaBaru = preference.hargaTerbaruKamar
            dewasa = preference.dewasa.toString()
            anak = preference.anak.toString()
            checkin = preference.checkin
            checkout = preference.checkout
            token = preference.token
            idCustomer = preference.idCustomer
        }
    }

    LaunchedEffect(key1 = reservasiState.res, block = {
        if (reservasiState.res != null) {
            navigateToResume(idResrvasi)
        }
    })

    var jumlahKamar by rememberSaveable { mutableStateOf("") }
    var jumlahFasilitas by rememberSaveable { mutableStateOf("") }
    var permintaan by rememberSaveable { mutableStateOf("") }
    var kamar by remember { mutableStateOf(listOf(KamarRequestItem(hargaBaru, 0, 0))) }

    viewModel.listFasilitas.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFasilitas(token)
            }
            is UiState.Success -> {
                fasilitas = uiState.data.data
                Log.d("dropdown1", fasilitas.toString())
                Log.d("dropdown2", uiState.data.data.toString())
            }
            is UiState.Error -> {}
        }
    }

    viewModel.listJenisKamar.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getJenisKamar()

            }
            is UiState.Success -> {
                val jenisKamar = convertToData(uiState.data.data, id)
                namaJenisKamar = jenisKamar!!.nama
            }
            is UiState.Error -> {}
        }
    }

    viewModel.addReservasiResponse.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {

            }
            is UiState.Success -> {
//                showToast(context, uiState.data.message)
//                navigateToResume(uiState.data.data.idReservasi)
                idResrvasi = uiState.data.data.idReservasi
            }
            is UiState.Error -> {}
        }
    }


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .clickable { navigateBack() }
        )

        Text(
            text = "Input Reservasi",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )

        Row {
            OutlinedTextField(
                value = namaJenisKamar,
                onValueChange = {namaJenisKamar = it},
                label = { Text("Kamar") },
                shape = RoundedCornerShape(24.dp),
                enabled = false,
                singleLine =  true,
                leadingIcon = {
                    Icon(Icons.Filled.MeetingRoom, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(0.7f)
            )

            OutlinedTextField(
                value = jumlahKamar,
                onValueChange = {jumlahKamar = it},
                label = { Text("Jumlah") },
                shape = RoundedCornerShape(24.dp),
                singleLine =  true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(0.3f)
            )
        }

        Row {
            OutlinedTextField(
                value = idFasilitas,
                onValueChange = {idFasilitas = it},
                label = { Text("Fasilitas") },
                shape = RoundedCornerShape(24.dp),
                singleLine =  true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(0.7f)
//                    .onGloballyPositioned { coordinates ->
//                        // This value is used to assign to
//                        // the DropDown the same width
//                        mTextFieldSize = coordinates.size.toSize()
//                    }
                ,
//                trailingIcon = {
//                    Icon(icon,"contentDescription",
//                        Modifier.clickable { mExpanded = !mExpanded })
//                }
            )

//            DropdownMenu(
//                expanded = mExpanded,
//                onDismissRequest = { mExpanded = false },
//                modifier = Modifier
//                    .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
//            ) {
//                fasilitas.forEach { label ->
//                    Log.d("dropdown3", label.toString())
//                    DropdownMenuItem(
//                        text = { Text(text = label.namaLayanan)},
//                        onClick = {
//                            mSelectedText = label.namaLayanan
//                            mSelectedId = label.idLayanan.toString()
//                            mExpanded = false
//                        }
//                    )
//                }
//            }

            OutlinedTextField(
                value = jumlahFasilitas,
                onValueChange = {jumlahFasilitas = it},
                label = { Text("Jumlah") },
                shape = RoundedCornerShape(24.dp),
                singleLine =  true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(0.3f)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = permintaan,
                onValueChange = {permintaan = it},
                label = { Text("Permintaan Khusus") },
                shape = RoundedCornerShape(24.dp),
                singleLine =  true,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            FilledTonalButton(
                onClick = {
//                navigateToBooking(id)
                    val listKamar: List<KamarRequestItem> = listOf(
                        KamarRequestItem(
                            idJenisKamar = id,
                            hargaPerMalam = hargaBaru,
                            jumlah = jumlahKamar.toInt()
                        ),
                    )

                    val listFasilitas: List<FasilitasRequestItem> = listOf(
                        FasilitasRequestItem(
                            idLayanan = idFasilitas.toInt(),
                            jumlah = jumlahFasilitas.toInt()
                        )
                    )

                    println("List Kamar: ${listKamar.joinToString()}")

                    viewModel.addReservasi(
                        token = token,
                        id = idCustomer,
                        checkin = convertDateFormat(checkin),
                        checkout = convertDateFormat(checkout),
                        dewasa = dewasa.toInt(),
                        anak = anak.toInt(),
                        permintaan = permintaan,
                        kamar = listKamar,
                        fasilitas = listFasilitas
                    )
                },
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(0.6f)
            ) {
                Text(text = "Buat Reservasi")
            }
        }
    }
}

fun convertToData(list: List<JenisKamarResponseItem>, id: Int): JenisKamarResponseItem? {
    return list.find { it.idJenisKamar == id }
}
package com.vvwxx.gahandroid.ui.screen.reservasi

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vvwxx.gahandroid.R
import com.vvwxx.gahandroid.data.remote.response.JenisKamarResponseItem
import com.vvwxx.gahandroid.di.Injection
import com.vvwxx.gahandroid.ui.ViewModelFactory
import com.vvwxx.gahandroid.ui.common.UiState
import com.vvwxx.gahandroid.util.convertToRupiah

@Composable
fun DetailKetersediaanScreen(
    modifier: Modifier = Modifier,
    id: Int,
    navigateBack: () -> Unit,
    context: Context = LocalContext.current,
    viewModel: ReservasiViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
) {

    val preferenceState by viewModel.getAccountPref.collectAsState(initial = null)

    var hargaBaru by rememberSaveable {
        mutableIntStateOf(0)
    }

    LaunchedEffect(preferenceState) {
        val preference = preferenceState
        if (preference != null) {
            hargaBaru = preference.hargaTerbaruKamar
        }
    }

    val img = when(id) {
        1 -> R.drawable.superior_room
        2 -> R.drawable.deluxe_room
        3 -> R.drawable.executive_room
        else -> R.drawable.junior_room
    }

    viewModel.listJenisKamar.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getJenisKamar()
            }
            is UiState.Success -> {
                val kamar = convertToData(uiState.data.data, id)
                DetailContent(
                    nama = kamar!!.nama,
                    deskripsi = kamar.deskripsi,
                    harga = hargaBaru.toString(),
                    tipeBed = kamar.tipeBed,
                    ukuran = kamar.ukuranKamar.toString(),
                    rincian = kamar.rincianKamar,
                    kapasitas = kamar.kapasitas,
                    img = img,
                    onBackClick = navigateBack
                )

            }
            is UiState.Error -> {}
        }
    }

}

fun convertToData(list: List<JenisKamarResponseItem>, id: Int): JenisKamarResponseItem? {
    return list.find { it.idJenisKamar == id }
}

@Composable
fun DetailContent(
    nama: String,
    deskripsi: String,
    harga: String,
    tipeBed: String,
    ukuran: String,
    rincian: String,
    img: Int,
    kapasitas: Int,
    context: Context = LocalContext.current,
    viewModel: ReservasiViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .clickable { onBackClick() }
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
//                .padding(16.dp)
        ) {
            Text(
                text = nama,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(top = 16.dp)

            )
        }

        Image(
            painter = painterResource(id = img),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(8.dp))
                .padding(top = 16.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = convertToRupiah(harga.toInt()),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(top = 8.dp)
        )

        Text(
            text = "Kapasitas: $kapasitas",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(top = 8.dp)
        )

        Text(
            text = "Tipe bed: $tipeBed",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(top = 8.dp)
        )

        Text(
            text = "Ukuran Kamar: $ukuran meter",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(top = 8.dp)
        )

        Text(
            text = "Deskripsi: ",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(top = 16.dp)
        )

        val descArray = deskripsi.split(", ")
        println(descArray)

        descArray.forEach {
            Text(
                text = it,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Normal
                ),
            )
        }

        Text(
            text = "Rincian Kamar: ",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(top = 16.dp)
        )

        val rincianArray = rincian.split(". ")

        rincianArray.forEach {
            println(it)
            Text(
                text = it,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Normal
                ),
            )
        }
    }
}
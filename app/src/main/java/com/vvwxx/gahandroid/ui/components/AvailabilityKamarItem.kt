package com.vvwxx.gahandroid.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vvwxx.gahandroid.R
import com.vvwxx.gahandroid.data.remote.response.AvailabilityKamarResponse
import com.vvwxx.gahandroid.data.remote.response.FKKamarInJenisKamar
import com.vvwxx.gahandroid.ui.theme.GahandroidTheme
import com.vvwxx.gahandroid.util.convertToRupiah

@Composable
fun AvailabilityKamarItem(
    jenisKamar: AvailabilityKamarResponse,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier.width(360.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
    ) {

        val img = when(jenisKamar.idJenisKamar) {
            1 -> R.drawable.superior_room
            2 -> R.drawable.deluxe_room
            3 -> R.drawable.executive_room
            else -> R.drawable.junior_room
        }

        Column {
            Image(
                painter = painterResource(id = img
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, end = 16.dp, top = 8.dp)) {

                Row {
                    Text(
                        text = jenisKamar.jenisKamar,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .weight(0.8f)
                    )

                    Icon(
                        imageVector = Icons.Filled.PersonOutline,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .width(20.dp)
                    )

                    Text(
                        text = jenisKamar.fKKamarInJenisKamar.kapasitas.toString(),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier
                            .padding(top = 2.dp)
                    )
                }

                Text(
                    text = "Tersedia ${jenisKamar.jumlahKamar} kamar",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(top = 2.dp)
                )

                Text(
                    text = jenisKamar.fKKamarInJenisKamar.tipeBed,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .padding(top = 2.dp)
                )

                Text(
                    text = "${jenisKamar.fKKamarInJenisKamar.ukuranKamar} m2",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .padding(top = 2.dp)
                )

                Box (modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = convertToRupiah(jenisKamar.hargaTerbaru),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier
                            .padding(top = 18.dp, bottom = 4.dp)
                            .align(Alignment.BottomEnd)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AvailabilityKamarItemPreview() {
    GahandroidTheme {
        AvailabilityKamarItem(
            jenisKamar = AvailabilityKamarResponse(
                namaSeason = "Natal dan tahun baru",
                idJenisKamar = 1,
                jumlahKamar = 2,
                tarifDasar = 100000,
                hargaTerbaru = 300000,
                jenisKamar = "Superior",
                tarifHarga = 200000,
                jenisSeason = "high",
                fKKamarInJenisKamar = FKKamarInJenisKamar(
                    rincianKamar = "AC, Air minum kemasan gratis, Brankas dalam kamar (ukuran laptop), Fasilitas membuat kopi/teh, Jubah mandi, Layanan kamar (24 jam), Meja tulis, Minibar, Pembersihan kamar harian, Pengering rambut, Peralatan mandi gratis, Sandal, Telepon, Tempat tidur eks",
                    nama = "Superior",
                    ukuranKamar = 22,
                    idJenisKamar = 1,
                    tarifDasar = 100000,
                    deskripsi = "Internet - WiFi Gratis",
                    kapasitas = 2,
                    tipeBed = "1 Double atau 2 Twin"
                )
            )
        )
    }
}

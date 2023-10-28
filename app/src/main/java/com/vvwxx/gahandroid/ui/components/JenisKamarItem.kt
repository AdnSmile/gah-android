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
import com.vvwxx.gahandroid.data.model.JenisKamar
import com.vvwxx.gahandroid.ui.theme.GahandroidTheme

@Composable
fun JenisKamarItem(
    jenisKamar: JenisKamar,
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
        Column {
            Image(
                painter = painterResource(id = jenisKamar.image),
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
                        text = jenisKamar.nama,
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
                        text = jenisKamar.kapasitas.toString(),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier
                            .padding(top = 2.dp)
                    )
                }

                Text(
                    text = jenisKamar.tipeBed,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .padding(top = 2.dp)
                )

                Text(
                    text = "${jenisKamar.ukuranKamar} m2",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .padding(top = 2.dp)
                )

                Box (modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "IDR ${jenisKamar.tarif}",
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
fun JenisKamarPreview() {
    GahandroidTheme {
        JenisKamarItem(
            jenisKamar = JenisKamar(
                id = 1,
                image = R.drawable.superior_room,
                nama = "Superior",
                deskripsi = "",
                kapasitas = 2,
                rincianKamar = "",
                tipeBed = "1 Double atau 2 Twin",
                ukuranKamar = 22,
                tarif = 300000
            )
        )
    }
}
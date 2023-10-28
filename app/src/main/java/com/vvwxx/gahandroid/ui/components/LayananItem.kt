package com.vvwxx.gahandroid.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vvwxx.gahandroid.R
import com.vvwxx.gahandroid.data.model.Layanan
import com.vvwxx.gahandroid.ui.theme.GahandroidTheme

@Composable
fun JenisItem(
    layanan: Layanan,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.width(140.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        
        Column {
            Image(
                painter = painterResource(id = layanan.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp))
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = layanan.nama,
                    maxLines = 2, // menentukan jumlah baris maksimal dari text
                    overflow = TextOverflow.Ellipsis, // mekanisme yang dilakukan jika ada teks yang lebih panjang daripada ruang yang ada
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )

                Text(
                    text = layanan.satuan,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal
                    ),
                )

                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "IDR ${layanan.tarif}",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier
                            .padding(top = 28.dp, bottom = 4.dp)
                            .align(Alignment.BottomStart)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JenisItemPrev() {
    GahandroidTheme {
        JenisItem(
            Layanan(
                id = 4,
                nama = "Meeting room",
                satuan = "per jam",
                tarif = 70000,
                image = R.drawable.massage,
            )
        )
    }
}
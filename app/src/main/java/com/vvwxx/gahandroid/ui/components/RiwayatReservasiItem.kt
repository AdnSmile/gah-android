package com.vvwxx.gahandroid.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vvwxx.gahandroid.ui.theme.GahandroidTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

@Composable
fun RiwayatReservasiItem(
    idBookin: String,
    status: String,
    startDate: Date,
    endDate: Date,
    modifier: Modifier = Modifier
) {

    ElevatedCard(
        modifier = modifier
            .width(360.dp)
            .height(160.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Row {
                Text(
                    text = "Booking ID $idBookin",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color.DarkGray,
                    modifier = Modifier
                        .weight(0.8f)
                )

                Icon(
                    imageVector = Icons.Filled.ArrowForwardIos,
                    contentDescription = null,
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .width(20.dp)
                )
            }

            Text(
                text = "${convertDateFormater(startDate)} • ${dateBetween(startDate, endDate)} malam",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Normal
                ),
                color = Color.Gray,
                modifier = Modifier.padding(top = 14.dp)
            )

            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(top = 14.dp)
            )

            Text(
                text = status,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Normal
                ),
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 14.dp)
            )
        }
    }
}

@SuppressLint("SimpleDateFormat")
private fun convertDateFormater(date: Date): String {

    val parseDate = SimpleDateFormat("E, dd MMM yyyy • hh:mm")
    return parseDate.format(date)
}

private fun dateBetween(start: Date, end: Date): String {

    val dif = end.time - start.time
    return TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS).toInt().toString()
}

@Preview(showBackground = true)
@Composable
fun RiwayatReservasiItemPrev() {
    GahandroidTheme {
        RiwayatReservasiItem(
            idBookin = "P200923-003",
            status = "Checkout",
            startDate = Date(),  //Date("2023-09-22 14:00:00.000000"),
            endDate =  Date()  //Date("2023-09-23 12:00:00.000000")
        )
    }
}
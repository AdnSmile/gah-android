package com.vvwxx.gahandroid.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vvwxx.gahandroid.data.model.JenisKamar
import com.vvwxx.gahandroid.data.model.Layanan
import com.vvwxx.gahandroid.di.Injection
import com.vvwxx.gahandroid.ui.ViewModelFactory
import com.vvwxx.gahandroid.ui.common.UiState
import com.vvwxx.gahandroid.ui.components.JenisKamarItem
import com.vvwxx.gahandroid.ui.components.LayananItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit,
) {

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllDataUmum()
            }
            is UiState.Success -> {

                HomeContent(
                    jenisKamar = uiState.data.jenisKamar,
                    layanan = uiState.data.layanan,
                    navigateToDetail = navigateToDetail,
                    modifier = modifier
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    jenisKamar: List<JenisKamar>,
    layanan: List<Layanan>,
    modifier: Modifier = Modifier,
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
                Text(
                    text = "Grand Atma Hotel",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                )
            }

            items(jenisKamar, key = { it.id }) { data ->
                JenisKamarItem(
                    jenisKamar = data,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.id)
                    }
                )
            }

            item {
                Text(
                    text = "Layanan Lain",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                )
            }

            item {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(layanan, key = {it.id}) { data ->
                            LayananItem(data)
                        }
                    }
                }
            }
        }
    }
}

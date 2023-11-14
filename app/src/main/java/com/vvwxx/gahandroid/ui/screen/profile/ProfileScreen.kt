package com.vvwxx.gahandroid.ui.screen.profile

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vvwxx.gahandroid.R
import com.vvwxx.gahandroid.di.Injection
import com.vvwxx.gahandroid.ui.ViewModelFactory
import com.vvwxx.gahandroid.ui.theme.GahandroidTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navigateBack: () -> Unit,
) {
    val preferenceState by viewModel.getAccountPref.collectAsState(initial = null)

    var role by rememberSaveable {
        mutableStateOf("")
    }

    LaunchedEffect(preferenceState) {
        val preference = preferenceState
        if (preference != null) {
            role = preference.role
        }
    }

//    ProfileContent(role = role) {
//        navigateBack()
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    username: String,
    fullname: String,
    role: String,
    email: String,
    noIdentitas: String,
    noTelpon: String,
    alamat: String,
    navigateBack: () -> Unit,
) {

    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = null,
        modifier = Modifier
            .clickable { navigateBack() }
            .padding(18.dp)
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Normal
            ),
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Image(
            painter = painterResource(id = R.drawable.user_profile),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = username,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Normal
            ),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            DataItem(title = "Nama Lengkat", value = fullname, disable = false)
            Spacer(modifier = Modifier.padding(8.dp))

            DataItem(title = "Alamat Email", value = email, disable = false)
            Spacer(modifier = Modifier.padding(8.dp))

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataItem(
    title: String,
    value: String,
    disable: Boolean
) {

    var textState by rememberSaveable { mutableStateOf(value) }

    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        ),
        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
    )

    OutlinedTextField(
        value = textState,
        onValueChange = { textState = it },
        enabled = false,
        singleLine = true,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
    )
    
}

@Preview(showBackground = true)
@Composable
fun ProfileContentPrev() {
    GahandroidTheme {
        ProfileContent(
            role = "sm",
            fullname = "Joko Susanto",
            username = "Halo Jok",
            email = "Joko@mail.com",
            noIdentitas = "567567436",
            noTelpon = "768876876",
            alamat = "Jl. Rumah baru"
        ) {

        }
    }
}
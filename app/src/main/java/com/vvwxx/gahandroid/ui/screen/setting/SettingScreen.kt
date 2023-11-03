package com.vvwxx.gahandroid.ui.screen.setting

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vvwxx.gahandroid.di.Injection
import com.vvwxx.gahandroid.ui.ViewModelFactory
import com.vvwxx.gahandroid.ui.components.AlertDialog
import com.vvwxx.gahandroid.ui.components.NotLoginScreen
import com.vvwxx.gahandroid.ui.theme.GahandroidTheme

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: SettingViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navigateToLogin: () -> Unit,
    navigateTolProfile: () -> Unit,
    navigateToHome: () -> Unit
) {

    val preferenceState by viewModel.getAccountPref.collectAsState(initial = null)

    var isLogin by rememberSaveable {
        mutableStateOf(false)
    }

    var idLogin by rememberSaveable {
        mutableStateOf(0)
    }

    var token by rememberSaveable {
        mutableStateOf("")
    }

    LaunchedEffect(preferenceState) {
        val preference = preferenceState
        if (preference != null) {
            isLogin = preference.isLogin
            token = preference.token
            idLogin = preference.id
        }
    }

    if (!isLogin) NotLoginScreen(navigateToLogin = navigateToLogin)
    else {
        SettingContent(
            viewModel = viewModel,
            navigateToHome = {
                navigateToHome()
            },
            navigateTolProfile = {
                navigateTolProfile()
            }
        )
    }


}

@Composable
fun SettingContent(
    modifier: Modifier = Modifier,
    viewModel: SettingViewModel,
    navigateToHome: () -> Unit,
    navigateTolProfile: () -> Unit
) {

    val openAlertDialog = remember { mutableStateOf(false) }

    if (openAlertDialog.value) {
        AlertDialog(
            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = {
                openAlertDialog.value = false
                println("Confirmation registered")
                viewModel.logout()
                navigateToHome()
            },
            dialogTitle = "Logout",
            dialogText = "Anda yakin ?",
            icon = Icons.Filled.Logout
        )
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Normal
            ),
        )

        Spacer(modifier = Modifier.padding(8.dp))

        SettingContentItem(title = "Profile", icon = Icons.Filled.Person) {
            navigateTolProfile()
        }

        Spacer(modifier = Modifier.padding(8.dp))

        SettingContentItem(title = "Logout", icon = Icons.Filled.Logout) {
            openAlertDialog.value = true
        }
    }
}

@Composable
fun SettingContentItem(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    click: () -> Unit
) {


    Card(
        modifier = modifier
            .width(360.dp)
            .height(50.dp)
            .clickable {
                click()
            },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {

            Row(
                horizontalArrangement = Arrangement.Center
            ){
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .weight(0.8f)
                        .padding(start = 16.dp)
                )

                Icon(
                    imageVector = Icons.Filled.ArrowForwardIos,
                    contentDescription = null,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SettingContentPrev() {
    GahandroidTheme {
//        SettingContent{
//
//        }
    }
}

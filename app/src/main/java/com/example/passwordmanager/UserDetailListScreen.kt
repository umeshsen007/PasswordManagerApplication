package com.example.passwordmanager

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.passwordmanager.ui.theme.PasswordManagerTheme
import com.example.passwordmanager.ui.theme.Purple40

@Composable
fun UserDetailListScreen(navController: NavController) {
    val focusManager = LocalFocusManager.current
    val bgColor = Purple40

    // view model objects

    // text fields objects

    //flag

    //permissions

    BackHandler {
        navController.navigateUp()
    }

    LaunchedEffect(key1 = true) {
        // Any side effects
    }

    StatusBarView(bgColor)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                /* to close keyboard on tap outside */
                detectTapGestures {
                    focusManager.clearFocus()
                }
            },
        topBar = {

        },
        snackbarHost = {

        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                Text("hello")

            }
        })
}

/* ************************************ Preview Start ************************************ */

@Preview(showBackground = true)
@Composable
fun UserDetailListScreenPreview() {
    PasswordManagerTheme {
        UserDetailListScreen(navController = rememberNavController())
    }
}
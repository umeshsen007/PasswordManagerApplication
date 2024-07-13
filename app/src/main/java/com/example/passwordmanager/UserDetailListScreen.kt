package com.example.passwordmanager

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.passwordmanager.ui.theme.PasswordManagerTheme
import com.example.passwordmanager.ui.theme.Purple40
import com.example.passwordmanager.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailListScreen(navController: NavController) {
    val topBgColor = Purple80

    // view model objects
    val filterStudentsDetail = getUserDetailDetailList()

    // text fields objects
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val accountNameTextField = remember { mutableStateOf("") }
    val userNameTextField = remember { mutableStateOf("") }
    val passwordTextField = remember { mutableStateOf("") }

    //permissions

    //flag
    val showBottomSheet = remember { mutableStateOf(false) }

    val wrapper = navController.previousBackStackEntry?.savedStateHandle?.get<NavArgWrapperDto2>("navArgWrapperDto")

    BackHandler {
        navController.navigateUp()
    }

    LaunchedEffect(key1 = true) {
        getUserDetailDetailList()
        if (wrapper?.navArgVo != null) {

        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

        },
        snackbarHost = {

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showBottomSheet.value = true
                },
            ) {
                Icon(Icons.Filled.Add, "")
            }
        },
        content = {
            // alert
            // camera launcher
            // dialog pop or bottom sheet
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .consumeWindowInsets(it)
            ) {
                if (showBottomSheet.value) {
                    ModalBottomSheetView(showBottomSheet, sheetState, scope, accountNameTextField, passwordTextField, userNameTextField)
                }
            }
        })
}
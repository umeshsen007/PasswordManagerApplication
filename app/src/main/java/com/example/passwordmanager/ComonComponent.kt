package com.example.passwordmanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ModalBottomSheetView(
    showBottomSheet: MutableState<Boolean>,
    sheetState: SheetState,
    scope: CoroutineScope,
    accountNameTextField: MutableState<String>,
    passwordTextField: MutableState<String>,
    userNameTextField: MutableState<String>
) {
    ModalBottomSheet(
        onDismissRequest = {
            showBottomSheet.value = false
        },
        sheetState = sheetState
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SimpleOutlinedTextFieldSample("Account Name", accountNameTextField)

            SimpleOutlinedTextFieldSample("Username/ Email", userNameTextField)

            PasswordTextField(passwordTextField)

            // Sheet content
            Button(
                onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet.value = false
                        }
                    }
                },
                modifier = Modifier.padding(top = 12.dp)
            ) {
                Text("Add New Account")
            }
        }
    }
}

@Composable
private fun PasswordTextField(passwordTextField: MutableState<String>) {
    OutlinedTextField(
        value = passwordTextField.value,
        onValueChange = { passwordTextField.value = it },
        label = { Text("Password") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

fun backNavigate(navController: NavController, wrapper: NavArgWrapperDto2?, destination: String) {
    navController.currentBackStackEntry?.savedStateHandle?.set("navArgWrapperDto", wrapper)
    navController.navigate(destination)
}

@Composable
fun SimpleOutlinedTextFieldSample(text: String, textFieldState: MutableState<String>) {

    OutlinedTextField(
        value = textFieldState.value,
        onValueChange = { textFieldState.value = it },
        label = { Text(text) },
        modifier = Modifier.fillMaxWidth().padding(4.dp)
    )
}
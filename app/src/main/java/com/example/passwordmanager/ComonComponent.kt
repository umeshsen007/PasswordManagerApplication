package com.example.passwordmanager

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.passwordmanager.dto.NavArgWrapperDto2
import com.example.passwordmanager.dto.UserDetailDto
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
    userNameTextField: MutableState<String>,
    selectedAccount: MutableState<UserDetailDto?>,
    onClick: () -> Unit,
    onDelete: (UserDetailDto) -> Unit,
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Button(
                    onClick = {
                        onClick()
                        showBottomSheet.value = false
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 6.dp, horizontal = 12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                    )
                ) {
                    if (selectedAccount.value != null) {
                        Text("Edit")
                    } else {
                        Text("Add New Account")
                    }
                }

                if (selectedAccount.value != null) {
                    Button(
                        onClick = {
                            selectedAccount.value?.let {
                                onDelete(it)
                            }
                            showBottomSheet.value = false
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 6.dp, horizontal = 12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                        )
                    ) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}

@Composable
fun PasswordTextField(passwordTextField: MutableState<String>) {
    val passwordVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = passwordTextField.value,
        onValueChange = { passwordTextField.value = it },
        label = { Text("Password") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        maxLines = 1,
        trailingIcon = {
            val image = if (passwordVisible.value) {
                painterResource(id = R.drawable.visibility_24dp_5f6368_fill0_wght400_grad0_opsz24)
            } else {
                painterResource(id = R.drawable.visibility_off_24dp_5f6368_fill0_wght400_grad0_opsz24)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Image(painter = image, contentDescription = "")
            }
        }
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        maxLines = 1
    )
}
package com.example.passwordmanager.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.passwordmanager.ModalBottomSheetView
import com.example.passwordmanager.dto.UserDetailDto
import com.example.passwordmanager.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailListScreen(
    navController: NavController,
    userDetailVm: UserDetailViewModel = viewModel()
) {
    // view model objects
    val accounts = userDetailVm.userDetailList.observeAsState()

    // text fields objects
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val accountNameTextField = remember { mutableStateOf("") }
    val userNameTextField = remember { mutableStateOf("") }
    val passwordTextField = remember { mutableStateOf("") }
    val selectedAccount = remember { mutableStateOf<UserDetailDto?>(null) }

    //permissions

    //flag
    val showBottomSheet = remember { mutableStateOf(false) }


    BackHandler {
        navController.navigateUp()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    accountNameTextField.value = ""
                    userNameTextField.value = ""
                    passwordTextField.value = ""
                    selectedAccount.value = null
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
                    .background(Color.LightGray)
                    .padding(it)
                    .consumeWindowInsets(it)
            ) {
                if (showBottomSheet.value) {
                    ModalBottomSheetView(
                        showBottomSheet,
                        sheetState,
                        scope,
                        accountNameTextField,
                        passwordTextField,
                        userNameTextField,
                        selectedAccount, onClick = {
                            if (selectedAccount.value == null) {
                                // Add the new account to the ViewModel's list
                                userDetailVm.addAccount(
                                    UserDetailDto(
                                        accountName = accountNameTextField.value,
                                        username = userNameTextField.value,
                                        password = passwordTextField.value
                                    )
                                )
                            } else {
                                // Update the existing account
                                userDetailVm.updateAccount(
                                    UserDetailDto(
                                        id = selectedAccount.value!!.id,
                                        accountName = accountNameTextField.value,
                                        username = userNameTextField.value,
                                        password = passwordTextField.value
                                    )
                                )
                            }
                        }, onDelete = {
                            userDetailVm.deleteAccount(it)
                            showBottomSheet.value = false
                        }
                    )
                }

                Text(
                    text = "Password Manager",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)
                )

                Divider()

                accounts.value?.let { accountList ->
                    if (accountList.isEmpty()) {
                        Text("No accounts available", modifier = Modifier.padding(16.dp))
                    } else {
                        accountList.forEach { account ->
                            account?.let {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .width(355.dp)
                                        .height(66.19.dp)
                                        .padding(vertical = 6.dp, horizontal = 12.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable {
                                            accountNameTextField.value = it.accountName
                                            userNameTextField.value = it.username
                                            passwordTextField.value = it.password
                                            selectedAccount.value = it
                                            showBottomSheet.value = true
                                        }
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = it.accountName,
                                            modifier = Modifier
                                                .weight(0.4f)
                                                .align(Alignment.CenterVertically)
                                                .padding(vertical = 6.dp, horizontal = 12.dp),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Start,
                                        )

                                        Text(
                                            text = "*******",
                                            modifier = Modifier
                                                .weight(0.4f)
                                                .align(Alignment.CenterVertically)
                                                .padding(vertical = 6.dp, horizontal = 12.dp),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Start,
                                            color = Color.DarkGray
                                        )

                                        Icon(
                                            imageVector = Icons.Default.KeyboardArrowRight,
                                            contentDescription = "Edit",
                                            modifier = Modifier
                                                .weight(0.2f)
                                                .size(50.dp)
                                                .padding(vertical = 6.dp, horizontal = 12.dp),
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        })
}
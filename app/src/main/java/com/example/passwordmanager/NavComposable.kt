package com.example.passwordmanager

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object NavDest {
    const val userDetailListScreen = "userDetailListScreen"
}


@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavDest.userDetailListScreen,
) {
    val activity = LocalContext.current as MainActivity

    NavHost(navController = navController, startDestination) {
        composable(NavDest.userDetailListScreen) { UserDetailListScreen(navController) }
    }
}
package com.lojbrooks.hospitals.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun HospitalsApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "hospitals") {
        composable("hospitals") {
            HospitalListScreen(viewModel = hiltViewModel())
        }
    }
}
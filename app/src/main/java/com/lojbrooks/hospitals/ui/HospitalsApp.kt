package com.lojbrooks.hospitals.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lojbrooks.hospitals.ui.hospitaldetail.HospitalDetailScreen
import com.lojbrooks.hospitals.ui.hospitallist.HospitalListScreen

@Composable
fun HospitalsApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "hospitals") {
        composable("hospitals") {
            HospitalListScreen(
                viewModel = hiltViewModel(),
                navigateToHospitalDetail = { navController.navigate("hospitals/$it") }
            )
        }
        composable(
            route = "hospitals/{orgId}",
            arguments = listOf(navArgument("orgId") { type = NavType.IntType })
        ) {
            HospitalDetailScreen(
                viewModel = hiltViewModel(),
                onNavigateUp = { navController.popBackStack() }
            )
        }
    }
}
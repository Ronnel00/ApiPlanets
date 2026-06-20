package edu.ucne.apiplanets.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.apiplanets.presentation.detail.DetailScreen
import edu.ucne.apiplanets.presentation.list.ListScreen

@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.List
    ) {
        composable<Screen.List> {
            ListScreen(
                onPlanetClick = { id ->
                    navHostController.navigate(Screen.Detail(id))
                }
            )
        }
        composable<Screen.Detail> {
            DetailScreen(
                onBack = { navHostController.navigateUp() }
            )
        }
    }
}
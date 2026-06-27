package edu.ucne.apiplanets.presentation.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.apiplanets.presentation.character.detail.CharacterDetailScreen
import edu.ucne.apiplanets.presentation.character.list.CharacterListScreen
import edu.ucne.apiplanets.presentation.detail.DetailScreen
import edu.ucne.apiplanets.presentation.list.ListScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavHost(navHostController: NavHostController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    DrawerMenu(
        drawerState = drawerState,
        navController = navHostController
    ) {
        NavHost(
            navController = navHostController,
            startDestination = Screen.PlanetList
        ) {
            composable<Screen.PlanetList> {
                ListScreen(
                    onPlanetClick = { id ->
                        navHostController.navigate(Screen.PlanetDetail(id))
                    },
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            }
            composable<Screen.PlanetDetail> {
                DetailScreen(
                    onBack = { navHostController.navigateUp() }
                )
            }
            composable<Screen.CharacterList> {
                CharacterListScreen(
                    onCharacterClick = { id ->
                        navHostController.navigate(Screen.CharacterDetail(id))
                    },
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            }
            composable<Screen.CharacterDetail> {
                CharacterDetailScreen(
                    onBack = { navHostController.navigateUp() }
                )
            }
        }
    }
}
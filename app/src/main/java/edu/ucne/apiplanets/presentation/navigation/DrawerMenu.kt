package edu.ucne.apiplanets.presentation.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun DrawerMenu(
    drawerState: DrawerState,
    navController: NavHostController,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    var selectedItem by remember { mutableStateOf("Planetas") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(280.dp)) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Dragon Ball API",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            Icons.Filled.Public,
                            contentDescription = "Planetas",
                            tint = if (selectedItem == "Planetas") Color.Black else Color.Gray
                        )
                    },
                    label = { Text("Planetas") },
                    selected = selectedItem == "Planetas",
                    onClick = {
                        selectedItem = "Planetas"
                        navController.navigate(Screen.PlanetList) { launchSingleTop = true }
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "Personajes",
                            tint = if (selectedItem == "Personajes") Color.Black else Color.Gray
                        )
                    },
                    label = { Text("Personajes") },
                    selected = selectedItem == "Personajes",
                    onClick = {
                        selectedItem = "Personajes"
                        navController.navigate(Screen.CharacterList) { launchSingleTop = true }
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        content()
    }
}
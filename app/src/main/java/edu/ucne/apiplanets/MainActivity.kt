package edu.ucne.apiplanets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.apiplanets.presentation.navigation.AppNavHost
import edu.ucne.apiplanets.ui.theme.ApiPlanetsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiPlanetsTheme {
                val navController = rememberNavController()
                AppNavHost(navHostController = navController)
            }
        }
    }
}
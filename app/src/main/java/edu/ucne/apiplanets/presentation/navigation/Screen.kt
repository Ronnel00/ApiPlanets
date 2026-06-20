package edu.ucne.apiplanets.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable data object List : Screen()
    @Serializable data class Detail(val id: Int) : Screen()
}
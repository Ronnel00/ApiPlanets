package edu.ucne.apiplanets.presentation.list

import edu.ucne.apiplanets.domain.model.Planet

data class ListUiState(
    val isLoading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val error: String? = null,
    val filterName: String = ""
)
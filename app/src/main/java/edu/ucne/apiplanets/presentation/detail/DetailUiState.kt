package edu.ucne.apiplanets.presentation.detail

import edu.ucne.apiplanets.domain.model.Planet

data class DetailUiState(
    val isLoading: Boolean = false,
    val planet: Planet? = null,
    val error: String? = null
)
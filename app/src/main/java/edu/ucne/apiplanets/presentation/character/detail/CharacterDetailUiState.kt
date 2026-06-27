package edu.ucne.apiplanets.presentation.character.detail

import edu.ucne.apiplanets.domain.model.Character

data class CharacterDetailUiState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val error: String? = null
)
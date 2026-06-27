package edu.ucne.apiplanets.presentation.character.list

import edu.ucne.apiplanets.domain.model.Character

data class CharacterListUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String? = null,
    val filterName: String = "",
    val filterRace: String = "",
    val filterGender: String = ""
)
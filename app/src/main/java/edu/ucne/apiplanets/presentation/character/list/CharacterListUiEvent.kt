package edu.ucne.apiplanets.presentation.character.list

sealed interface CharacterListUiEvent {
    data class UpdateFilterName(val name: String) : CharacterListUiEvent
    data class UpdateFilterRace(val race: String) : CharacterListUiEvent
    data class UpdateFilterGender(val gender: String) : CharacterListUiEvent
    data object Search : CharacterListUiEvent
}
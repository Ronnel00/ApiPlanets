package edu.ucne.apiplanets.presentation.list

sealed interface ListUiEvent {
    data class UpdateFilterName(val name: String) : ListUiEvent
    data object Search : ListUiEvent
}
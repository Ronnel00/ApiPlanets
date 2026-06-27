package edu.ucne.apiplanets.presentation.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.apiplanets.data.remote.Resource
import edu.ucne.apiplanets.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterListUiState())
    val state: StateFlow<CharacterListUiState> = _state.asStateFlow()

    init { loadCharacters() }

    fun onEvent(event: CharacterListUiEvent) {
        when (event) {
            is CharacterListUiEvent.UpdateFilterName -> _state.update {
                it.copy(filterName = event.name)
            }
            is CharacterListUiEvent.UpdateFilterRace -> _state.update {
                it.copy(filterRace = event.race)
            }
            is CharacterListUiEvent.UpdateFilterGender -> _state.update {
                it.copy(filterGender = event.gender)
            }
            CharacterListUiEvent.Search -> loadCharacters()
        }
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            val current = _state.value
            getCharactersUseCase(
                name = current.filterName.takeIf { it.isNotBlank() }
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update {
                        it.copy(isLoading = true, error = null)
                    }
                    is Resource.Success -> {
                        val filtered = (result.data ?: emptyList()).filter { character ->
                            (current.filterRace.isBlank() ||
                                    character.race.contains(current.filterRace, ignoreCase = true)) &&
                                    (current.filterGender.isBlank() ||
                                            character.gender.contains(current.filterGender, ignoreCase = true))
                        }
                        _state.update {
                            it.copy(isLoading = false, characters = filtered)
                        }
                    }
                    is Resource.Error -> _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }
}
package edu.ucne.apiplanets.domain.usecase

import edu.ucne.apiplanets.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(
        page: Int = 1,
        limit: Int = 20,
        name: String? = null
    ) = repository.getCharacters(page, limit, name)
}
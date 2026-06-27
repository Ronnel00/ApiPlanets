package edu.ucne.apiplanets.domain.repository

import edu.ucne.apiplanets.data.remote.Resource
import edu.ucne.apiplanets.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(
        page: Int,
        limit: Int,
        name: String?
    ): Flow<Resource<List<Character>>>

    fun getCharacterDetail(id: Int): Flow<Resource<Character>>
}
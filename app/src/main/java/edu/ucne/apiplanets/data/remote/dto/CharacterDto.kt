package edu.ucne.apiplanets.data.remote.dto

import edu.ucne.apiplanets.domain.model.Character

data class CharacterResponseDto(
    val items: List<CharacterDto>
)

data class CharacterDto(
    val id: Int,
    val name: String,
    val race: String? = null,
    val gender: String? = null,
    val description: String? = null,
    val image: String? = null
) {
    fun toDomain() = Character(
        id = id,
        name = name,
        race = race ?: "",
        gender = gender ?: "",
        description = description ?: "",
        image = image ?: ""
    )
}
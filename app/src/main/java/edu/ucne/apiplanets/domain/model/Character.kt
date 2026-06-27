package edu.ucne.apiplanets.domain.model

data class Character(
    val id: Int,
    val name: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String
)
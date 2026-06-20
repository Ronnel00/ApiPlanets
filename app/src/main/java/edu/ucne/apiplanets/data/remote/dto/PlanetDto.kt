package edu.ucne.apiplanets.data.remote.dto

import com.squareup.moshi.JsonClass
import edu.ucne.apiplanets.domain.model.Planet

@JsonClass(generateAdapter = true)
data class PlanetResponseDto(
    val items: List<PlanetDto>
)

@JsonClass(generateAdapter = true)
data class PlanetDto(
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
    val image: String
) {
    fun toDomain() = Planet(
        id = id,
        name = name,
        isDestroyed = isDestroyed,
        description = description,
        image = image
    )
}
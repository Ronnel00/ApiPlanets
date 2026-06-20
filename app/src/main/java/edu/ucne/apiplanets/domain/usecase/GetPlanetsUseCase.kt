package edu.ucne.apiplanets.domain.usecase

import edu.ucne.apiplanets.domain.repository.PlanetRepository
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    operator fun invoke(
        page: Int = 1,
        limit: Int = 20,
        name: String? = null
    ) = repository.getPlanets(page, limit, name)
}
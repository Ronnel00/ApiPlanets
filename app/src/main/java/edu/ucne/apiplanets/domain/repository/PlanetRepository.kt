package edu.ucne.apiplanets.domain.repository

import edu.ucne.apiplanets.data.remote.Resource
import edu.ucne.apiplanets.domain.model.Planet
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {
    fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Flow<Resource<List<Planet>>>

    fun getPlanetDetail(id: Int): Flow<Resource<Planet>>
}
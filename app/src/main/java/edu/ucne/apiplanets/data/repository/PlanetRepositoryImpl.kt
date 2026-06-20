package edu.ucne.apiplanets.data.repository

import edu.ucne.apiplanets.data.remote.DragonBallApi
import edu.ucne.apiplanets.data.remote.Resource
import edu.ucne.apiplanets.domain.model.Planet
import edu.ucne.apiplanets.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val api: DragonBallApi
) : PlanetRepository {

    override fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Flow<Resource<List<Planet>>> = flow {
        emit(Resource.Loading())
        try {
            if (name.isNullOrBlank()) {
                val response = api.getPlanets(page, limit)
                if (response.isSuccessful) {
                    val planets = response.body()!!.items.map { it.toDomain() }
                    emit(Resource.Success(planets))
                } else {
                    emit(Resource.Error("Error de red ${response.code()}"))
                }
            } else {
                val response = api.getPlanetsByName(name)
                if (response.isSuccessful) {
                    val planets = response.body()!!.map { it.toDomain() }
                    emit(Resource.Success(planets))
                } else {
                    emit(Resource.Error("Error de red ${response.code()}"))
                }
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Error de servidor: ${e.message}"))
        } catch (e: Exception) {
            emit(Resource.Error("Error desconocido: ${e.message}"))
        }
    }

    override fun getPlanetDetail(id: Int): Flow<Resource<Planet>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getPlanetDetail(id)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.toDomain()))
            } else {
                emit(Resource.Error("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Error de servidor: ${e.message}"))
        } catch (e: Exception) {
            emit(Resource.Error("Error desconocido: ${e.message}"))
        }
    }
}
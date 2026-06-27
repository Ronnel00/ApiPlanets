package edu.ucne.apiplanets.data.repository

import edu.ucne.apiplanets.data.remote.DragonBallApi
import edu.ucne.apiplanets.data.remote.Resource
import edu.ucne.apiplanets.domain.model.Character
import edu.ucne.apiplanets.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: DragonBallApi
) : CharacterRepository {

    override fun getCharacters(
        page: Int,
        limit: Int,
        name: String?
    ): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading())
        try {
            if (name.isNullOrBlank()) {
                val response = api.getCharacters(page, limit)
                if (response.isSuccessful) {
                    emit(Resource.Success(response.body()!!.items.map { it.toDomain() }))
                } else {
                    emit(Resource.Error("Error ${response.code()}"))
                }
            } else {
                val response = api.getCharactersByName(name)
                if (response.isSuccessful) {
                    emit(Resource.Success(response.body()!!.map { it.toDomain() }))
                } else {
                    emit(Resource.Error("Error ${response.code()}"))
                }
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Error de servidor: ${e.message}"))
        } catch (e: Exception) {
            emit(Resource.Error("Error desconocido: ${e.message}"))
        }
    }

    override fun getCharacterDetail(id: Int): Flow<Resource<Character>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getCharacterDetail(id)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.toDomain()))
            } else {
                emit(Resource.Error("Error ${response.code()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Error de servidor: ${e.message}"))
        } catch (e: Exception) {
            emit(Resource.Error("Error desconocido: ${e.message}"))
        }
    }
}
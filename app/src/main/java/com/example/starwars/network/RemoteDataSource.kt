package com.example.starwars.network

import com.example.starwars.models.CharactersList
import com.example.starwars.utils.Result
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) : BaseDataSource(), IRemoteDataSource {

    override suspend fun fetchCharacters(pageNo: Int): Result<CharactersList> =
        getResult { apiService.getCharacters(1) }

}

interface IRemoteDataSource {
    suspend fun fetchCharacters(pageNo: Int): Result<CharactersList>
}
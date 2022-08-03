package com.example.starwars.network

import com.example.starwars.models.CharactersList
import com.example.starwars.utils.Result

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource(), IRemoteDataSource {

    override suspend fun fetchCharacters(pageNo: Int): com.example.starwars.utils.Result<CharactersList> =
        getResult { apiService.getCharacters(1) }

}

interface IRemoteDataSource {
    suspend fun fetchCharacters(pageNo: Int): Result<CharactersList>
}
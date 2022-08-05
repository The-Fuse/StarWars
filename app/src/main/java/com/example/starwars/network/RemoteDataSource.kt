package com.example.starwars.network

import com.example.starwars.models.CharacterFilm
import com.example.starwars.models.CharactersList
import com.example.starwars.utils.Result
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) : BaseDataSource(), IRemoteDataSource {

    override suspend fun fetchCharacters(pageNo: Int): Result<CharactersList> =
        getResult { apiService.getCharacters(pageNo) }

    override suspend fun fetchFilm(id: Int): Result<CharacterFilm> =
        getResult { apiService.getFilm(id) }


}

interface IRemoteDataSource {
    suspend fun fetchCharacters(pageNo: Int): Result<CharactersList>
    suspend fun fetchFilm(id: Int): Result<CharacterFilm>
}
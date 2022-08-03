package com.example.starwars.network

import com.example.starwars.models.Character
import com.example.starwars.models.CharactersList
import com.example.starwars.utils.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/people")
    suspend fun getCharacters(
        @Query("page") page: Int = 1,
    ): Response<CharactersList>

    @GET("/people/{id}/")
    suspend fun getSet(@Path("id") id: String): Response<Character>

}
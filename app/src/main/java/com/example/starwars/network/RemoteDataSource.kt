package com.example.starwars.network

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource(){

    suspend fun fetchCharacters(pageNo: Int) = getResult { apiService.getCharacters(pageNo) }

}
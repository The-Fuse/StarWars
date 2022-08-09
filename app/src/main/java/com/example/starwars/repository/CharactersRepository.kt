package com.example.starwars.repository

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.starwars.database.DatabaseDao
import com.example.starwars.database.ILocalDataSource
import com.example.starwars.database.LocalDataSource
import com.example.starwars.models.Character
import com.example.starwars.models.CharacterFilm
import com.example.starwars.network.ApiService
import com.example.starwars.network.IRemoteDataSource
import com.example.starwars.network.RemoteDataSource
import com.example.starwars.paging.CharacterPagingSource
import com.example.starwars.paging.CharacterRemoteMediator
import com.example.starwars.utils.Result
import javax.inject.Inject

@ExperimentalPagingApi
class CharactersRepository @Inject constructor(
    private val localDataSource: ILocalDataSource,
    private val remoteDataSource: IRemoteDataSource,
    private val mediator: CharacterRemoteMediator
) {
//    val characters: LiveData<Result<List<Character>>> = resultLiveData(
//        databaseQuery = {localDataSource.getCharactersList() },
//        networkCall = {remoteDataSource.fetchCharacters(1)},
//        saveCallResult = {localDataSource.insertCharacters(it.results)}
//    )

    suspend fun filmDetail(id:Int): Result<CharacterFilm> =
        remoteDataSource.fetchFilm(id)

    fun getCharactersCount() =  Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 200),
        remoteMediator = mediator ,
        pagingSourceFactory = { localDataSource.getCharactersList()}
    ).liveData


}
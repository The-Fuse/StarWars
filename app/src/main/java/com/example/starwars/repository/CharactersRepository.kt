package com.example.starwars.repository

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.starwars.database.DatabaseDao
import com.example.starwars.database.ILocalDataSource
import com.example.starwars.database.LocalDataSource
import com.example.starwars.models.Character
import com.example.starwars.network.IRemoteDataSource
import com.example.starwars.network.RemoteDataSource
import com.example.starwars.paging.CharacterPagingSource
import com.example.starwars.utils.Result
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val localDataSource: ILocalDataSource,
    private val remoteDataSource: IRemoteDataSource
) {
    val characters: LiveData<Result<List<Character>>> = resultLiveData(
        databaseQuery = {localDataSource.getCharactersList() },
        networkCall = {remoteDataSource.fetchCharacters(1)},
        saveCallResult = {localDataSource.insertCharacters(it.results)}
    )

    fun getCharactersCount(): LiveData<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 200),
        pagingSourceFactory = { CharacterPagingSource(remoteDataSource) }
    ).liveData


}
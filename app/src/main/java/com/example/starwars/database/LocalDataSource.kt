package com.example.starwars.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.example.starwars.models.Character
import com.example.starwars.models.CharacterFilm
import javax.inject.Inject

class LocalDataSource @Inject constructor(localDatabase: LocalDatabase) : ILocalDataSource {

    private val dao = localDatabase.getDatabaseDao()
    override suspend fun insertCharacters(characters: List<Character>) {
        dao.insertCharacters(characters)
    }

    override fun getCharactersList(): PagingSource<Int,Character> = dao.getCharacters()

}

interface ILocalDataSource {
    suspend fun insertCharacters(characters: List<Character>)
    fun getCharactersList(): PagingSource<Int,Character>
}
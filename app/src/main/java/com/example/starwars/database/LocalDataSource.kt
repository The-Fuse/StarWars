package com.example.starwars.database

import androidx.lifecycle.LiveData
import com.example.starwars.models.Character

class LocalDataSource(localDatabase: LocalDatabase) : ILocalDataSource {

    private val dao = localDatabase.getDatabaseDao()
    override suspend fun insertCharacters(characters: List<Character>) {
        dao.insertCharacters(characters)
    }

    override fun getCharactersList(): LiveData<List<Character>> = dao.getCharacters()

}

interface ILocalDataSource {
    suspend fun insertCharacters(characters: List<Character>)
    fun getCharactersList(): LiveData<List<Character>>
}
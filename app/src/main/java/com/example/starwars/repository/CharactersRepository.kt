package com.example.starwars.repository

import androidx.lifecycle.LiveData
import com.example.starwars.database.DatabaseDao
import com.example.starwars.models.Character

class CharactersRepository(private val charactersDao: DatabaseDao) {

    fun getCharacters(): LiveData<List<Character>>{
        return charactersDao.getCharacters()
    }

    suspend fun insertCharacters(characters: List<Character>){
        charactersDao.insertCharacters(characters)
    }
}
package com.example.starwars.repository

import androidx.lifecycle.LiveData
import com.example.starwars.database.DatabaseDao
import com.example.starwars.models.Character
import com.example.starwars.utils.Result

class CharactersRepository(private val charactersDao: DatabaseDao) {

    fun getCharacters(): LiveData<Result<List<Character>>>{
        return charactersDao.getCharacters()
    }

    suspend fun insertCharacters(characters: List<Character>){
        charactersDao.insertCharacters(characters)
    }
}
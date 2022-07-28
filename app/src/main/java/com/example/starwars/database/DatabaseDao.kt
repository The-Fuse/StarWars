package com.example.starwars.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.starwars.models.Character

@Dao
interface DatabaseDao {

    @Query("SELECT * from  characters")
    fun getCharacters(): LiveData<List<Character>>

    @Insert
    suspend fun insertCharacters(characters: List<Character>)
}
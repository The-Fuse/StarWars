package com.example.starwars.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwars.models.Character
import com.example.starwars.utils.Result

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<Character>)

    @Query("SELECT * from  characters")
    fun getCharacters(): LiveData<List<Character>>

}
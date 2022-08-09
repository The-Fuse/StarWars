package com.example.starwars.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwars.models.Character
import com.example.starwars.utils.Result

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<Character>)

    @Query("SELECT * from  characters")
    fun getCharacters(): PagingSource<Int,Character>

    @Query("Delete from characters")
    suspend fun deleteCharacters()
}
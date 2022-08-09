package com.example.starwars.database

import androidx.room.*
import com.example.starwars.models.CharacterRemoteKeys

@Dao
interface RemoteKeysDao {

    @Query("SELECT * from CharacterRemoteKeys where id ==:id")
    suspend fun getRemoteKeys(id: String): CharacterRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRemoteKeys(remoteKeys: List<CharacterRemoteKeys>)

    @Query("DELETE FROM CharacterRemoteKeys")
    suspend fun deleteRemoteKeys()
}
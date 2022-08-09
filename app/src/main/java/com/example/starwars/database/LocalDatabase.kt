package com.example.starwars.database

import android.content.Context
import androidx.room.*
import com.example.starwars.models.Character
import com.example.starwars.models.CharacterRemoteKeys

@Database(entities = [Character::class,CharacterRemoteKeys::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun getDatabaseDao(): DatabaseDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
}
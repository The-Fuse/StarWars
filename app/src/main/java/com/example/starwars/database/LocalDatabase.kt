package com.example.starwars.database

import android.content.Context
import androidx.room.*
import com.example.starwars.models.Character

@Database(entities = [Character::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun getDatabaseDao(): DatabaseDao

}
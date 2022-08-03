package com.example.starwars.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun saveStringList(data: List<String>): String? {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun getIntList(data: String): List<String> {
        return Gson().fromJson(data, object : TypeToken<List<String>>() {}.type)
    }

}
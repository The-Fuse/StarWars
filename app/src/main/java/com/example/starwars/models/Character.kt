package com.example.starwars.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    val films: List<String>,
    val homeworld: String = "",
    val gender: String = "",
    val skinColor: String = "",
    val edited: String = "",
    val created: String = "",
    val mass: String = "",
    val vehicles: List<String>?,
    val url: String = "",
    val hairColor: String = "",
    val birthYear: String = "",
    val eyeColor: String = "",
    val starships: List<String>?,
    val name: String = "",
    val height: String = "")
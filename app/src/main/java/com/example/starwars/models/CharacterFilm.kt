package com.example.starwars.models

import androidx.room.Entity

@Entity(tableName = "films")
data class CharacterFilm(val edited: String = "",
                         val director: String = "",
                         val created: String = "",
                         val vehicles: List<String>?,
                         val opening_crawl: String = "",
                         val title: String = "",
                         val url: String = "",
                         val characters: List<String>?,
                         val episode_id: Int = 0,
                         val planets: List<String>?,
                         val release_date: String = "",
                         val starships: List<String>?,
                         val species: List<String>?,
                         val producer: String = "")
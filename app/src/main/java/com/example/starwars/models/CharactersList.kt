package com.example.starwars.models

data class CharactersList(val next: String = "",
                          val previous: String = "",
                          val count: Int = 0,
                          val results: List<Character>?)
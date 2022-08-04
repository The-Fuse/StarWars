package com.example.starwars.models

data class CharactersList(val next: String?,
                          val previous: String? = null,
                          val count: Int = 0,
                          val results: List<Character>)
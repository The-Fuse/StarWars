package com.example.starwars.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterRemoteKeys(

    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevKey: Int?,
    val nextKey: Int?
)

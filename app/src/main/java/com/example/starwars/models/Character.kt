package com.example.starwars.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "characters")
data class Character(
    val films: List<String>,
    val homeworld: String = "",
    val gender: String = "",
    val skin_color: String = "",
    val edited: String = "",
    val created: String = "",
    val mass: String = "",
    val vehicles: List<String>?,
    @PrimaryKey
    val url: String = "",
    val hair_color: String = "",
    val birth_year: String = "",
    val eye_color: String = "",
    val starships: List<String>?,
    val name: String = "",
    val height: String = ""
): Parcelable
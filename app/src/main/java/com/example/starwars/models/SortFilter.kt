package com.example.starwars.models

data class SortFilter(
    var id: Int,
    var name: String,
    var isSelected: Boolean = false
)

package com.example.treasure_hunt_fixed.model

data class Location(
    //everything will be string resources instead of hard-coded strings, hence the integer type
    val name: Int,
    val clue: Int,
    val hint: Int
)

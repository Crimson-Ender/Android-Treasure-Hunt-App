package com.example.treasure_hunt_fixed.data

data class Location(
    //everything will be string resources instead of hard-coded strings, hence the integer type
    val name: Int,
    val clue: Int,
    val hint: Int,
    val trivia:Int,
    val long: Double,
    val lat: Double,
) {
    companion object {

        fun createPlaceholder(): Location {
            return Location(
                name = com.example.treasure_hunt_fixed.R.string.placeholder_name,
                clue = com.example.treasure_hunt_fixed.R.string.placeholder_clue,
                hint = com.example.treasure_hunt_fixed.R.string.placeholder_hint,
                trivia = com.example.treasure_hunt_fixed.R.string.placeholder_trivia,
                long = 0.0,
                lat = 0.0
            )

        }
    }
}
/*
Author: Max Baker
Email: bakerm7@oregonstate.edu
OSU CS 492 - Mobile App Development
Final Project: Treasure Hunt
THLocation.kt
 */

package com.example.treasure_hunt_fixed.data

data class THLocation(
    //everything will be string resources instead of hard-coded strings, hence the integer type
    val name: Int,
    val clue: Int,
    val hint: Int,
    val trivia:Int,
    val img: Int,
    val long: Double,
    val lat: Double,
) {
    companion object {

        fun createPlaceholder(): THLocation {
            return THLocation(
                name = com.example.treasure_hunt_fixed.R.string.placeholder_name,
                clue = com.example.treasure_hunt_fixed.R.string.placeholder_clue,
                hint = com.example.treasure_hunt_fixed.R.string.placeholder_hint,
                trivia = com.example.treasure_hunt_fixed.R.string.placeholder_trivia,
                img = com.example.treasure_hunt_fixed.R.drawable.placeholder,
                long = -123.2729333,
                lat = 44.563125
            )

        }
    }
}
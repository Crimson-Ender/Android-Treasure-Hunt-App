package com.example.treasurehunt.model

import com.example.treasure_hunt_fixed.data.Location

data class THUiState(

    val permissionsGranted: Boolean = false, //default value, the user will have to manually enable location permissions
    val currentLocation: Location = Location.createPlaceholder(),

    val currentClue: Int = 1, //default value, set to a different value during the hunt

    val isCorrect: Boolean = false, //default value is false, set to true when the clue is correct
    val isGameOver: Boolean = false,

    val isHintRevealed: Boolean = false,

    val usingFinePosition: Boolean=false,

    val isTimeRunning: Boolean = true
)
package com.example.treasurehunt.model

data class THUiState(
    val currentClue: Int = 0, //default value, set to a different value during the hunt
    val isCorrect: Boolean = false //default value is false, set to true when the clue is correct

)
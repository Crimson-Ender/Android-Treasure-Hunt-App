package com.example.treasurehunt.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class THViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(THUiState())

    val uiState: StateFlow<THUiState> = _uiState.asStateFlow()

    init{
        resetApp()
    }

    private fun resetApp(){
        _uiState.value = THUiState(currentClue = 0)
    }

    fun onPermissionsChange(usingFine: Boolean, usingCoarse:Boolean){
        _uiState.value = _uiState.value.copy(
            permissionsGranted = usingFine || usingCoarse, usingFinePosition = usingFine
        )
    }
}
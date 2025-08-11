package com.example.treasurehunt.model

import android.location.Location
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.treasure_hunt_fixed.data.Datasource
import com.example.treasure_hunt_fixed.data.THLocation
import com.example.treasure_hunt_fixed.model.GeofenceUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class THViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(THUiState())

    val uiState: StateFlow<THUiState> = _uiState.asStateFlow()

    var locationsList: List<THLocation> = Datasource().loadLocations()

    init{
        resetApp()
    }

    private fun resetApp(){
        locationsList = Datasource().loadLocations()
        _uiState.value = THUiState(currentClue = 1, currentLocation = locationsList[0])
    }

    fun onPermissionsChange(usingFine: Boolean, usingCoarse:Boolean){
        _uiState.value = _uiState.value.copy(
            permissionsGranted = usingFine || usingCoarse, usingFinePosition = usingFine
        )
    }

    fun revealHint(){
        _uiState.value = _uiState.value.copy(
            isHintRevealed = true
        )
    }

    var guessedLocation by mutableStateOf<Location?>(null)

    fun checkLocationGuess(location: Location?){
        guessedLocation=location

        println("checkLocationGuess called")

        if(location==null) return

        //get the difference between the two points in kilometers
        val distDifference = GeofenceUtils.calculateDistance(_uiState.value.currentLocation.lat,
            _uiState.value.currentLocation.long,
            location.latitude,
            location.longitude)

        val distMeters = distDifference * 1000

        Log.d("TEST", "Distance: $distDifference km ($distMeters m)")
            _uiState.value=_uiState.value.copy(
                debugDistDiff = distDifference,
                isCorrect = distDifference <0.1

            )
        Log.d("TEST", "isCorrect = ${uiState.value.isCorrect}")

    }

    fun nextLocation(){
        val currentState = uiState.value
        val currentClue = uiState.value.currentClue + 1

        if (currentState.currentClue >3){
            //prevent indexing errors
            return
        }


        _uiState.value=_uiState.value.copy(
                currentLocation = locationsList[currentState.currentClue],
                currentClue = currentClue,
                isCorrect = false,
                isHintRevealed = false
        )

    }
}
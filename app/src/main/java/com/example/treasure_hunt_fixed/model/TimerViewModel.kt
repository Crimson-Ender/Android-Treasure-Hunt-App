/*
Author: Max Baker
Email: bakerm7@oregonstate.edu
OSU CS 492 - Mobile App Development
Final Project: Treasure Hunt
TimerViewModel.kt
 */

package com.example.treasure_hunt_fixed.model

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/*
this code based off of the tutorial "Creating a Timer Screen with Kotlin and Jetpack Compose in Android" by Tippu Fisal Sheriff on Medium:
https://medium.com/@TippuFisalSheriff/creating-a-timer-screen-with-kotlin-and-jetpack-compose-in-android-f7c56952d599
 */

class TimerViewModel: ViewModel() {

    private val _timer = MutableStateFlow(0L)
    val timer = _timer.asStateFlow()

    private var timerJob: Job? = null

    //called when the timer object is first instantiated.
    fun startTimer(){
        timerJob?.cancel()
        timerJob = viewModelScope.launch{

            while (true){
                delay(1000)
                _timer.value++
            }

        }
    }

    //called when the user guesses correctly
    fun pauseTimer(){
        timerJob?.cancel()
    }

    fun stopTimer(){
        _timer.value =0
        timerJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }

}
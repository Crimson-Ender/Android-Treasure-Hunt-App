/*
Author: Max Baker
Email: bakerm7@oregonstate.edu
OSU CS 492 - Mobile App Development
Final Project: Treasure Hunt
NextClueScreen.kt
 */


package com.example.treasure_hunt_fixed.ui

import android.graphics.Paint
import androidx.compose.runtime.Composable
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.location.Location
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.treasure_hunt_fixed.R
import com.example.treasure_hunt_fixed.model.TimerViewModel
import com.example.treasurehunt.model.THViewModel
import com.google.android.gms.location.Priority
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NextClueScreen(thViewModel: THViewModel = viewModel(),
                   timerViewModel: TimerViewModel=viewModel(),
                   onNextButtonClicked:()-> Unit,
                   modifier: Modifier= Modifier
){

    val uiState by thViewModel.uiState.collectAsState()
    val timeValue by timerViewModel.timer.collectAsState()
    timerViewModel.pauseTimer()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text= stringResource(R.string.clue_found_header),
                    textAlign = TextAlign.Center,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier= modifier
                    //.align(alignment= Alignment.Center)
                )
            },
                colors= TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Magenta),
                modifier = modifier
            )
        }
    ){innerPadding->
        Column(modifier.padding(innerPadding)){

            Spacer(modifier=modifier.height(70.dp))

            Box(modifier = modifier
                .padding(start = 10.dp, end = 10.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()){
                Text(text=timeValue.formatTime(),
                    fontSize = 90.sp,
                    fontWeight = FontWeight.Bold,
                    modifier=modifier.align(Alignment.Center))
            }

            Spacer(modifier=modifier.height(20.dp))

            Text(text=stringResource(R.string.nice_work),
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                modifier=modifier.align(Alignment.CenterHorizontally))

            Box(modifier=modifier
                .height(250.dp)
                .padding(top=20.dp)
                .fillMaxWidth()){
                Image(painterResource(uiState.currentLocation.img),
                    contentDescription = stringResource(uiState.currentLocation.name),
                    modifier = modifier.fillMaxSize().align(Alignment.Center))
            }

            Text(text=stringResource(uiState.currentLocation.name),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier=modifier.padding(bottom=10.dp)
                    .align(Alignment.CenterHorizontally))

            Box(modifier=modifier
                .height(200.dp)
                .padding(top=10.dp)
                .fillMaxWidth()){
                val triviaScrollState = rememberScrollState()
                Text(text=stringResource(uiState.currentLocation.trivia),
                    fontSize = 12.sp,
                    modifier=modifier.verticalScroll(triviaScrollState))
            }

            Button(onClick = {

                onNextButtonClicked()
                CoroutineScope(Dispatchers.Main).launch{
                    delay(300)
                    thViewModel.nextLocation()

                }

            },
                modifier=modifier
                    .padding(10.dp)
                    .fillMaxWidth()) {
                Text(stringResource(R.string.next_clue), fontWeight = FontWeight.SemiBold)
            }

        }
    }


}
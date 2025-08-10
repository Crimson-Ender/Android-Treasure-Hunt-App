package com.example.treasure_hunt_fixed.ui

import android.R
import android.adservices.topics.Topic
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.treasurehunt.model.THViewModel
import com.google.android.gms.location.Priority
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.treasure_hunt_fixed.model.TimerViewModel
import kotlinx.coroutines.flow.combine

@SuppressLint("DefaultLocale")
private fun Long.formatTime():String {
    //format the timer output so it isn't just one giant integer of just seconds counting up

    val hours = this/3600
    val minutes = (this %3600)/60
    val remainingSeconds = this%60

    return String.format("%02d:%02d:%02d",hours,minutes,remainingSeconds)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClueScreen(thViewModel: THViewModel = viewModel(),
               timerViewModel: TimerViewModel=viewModel(),
               onFoundButtonClicked: ()->Unit,
               onFinalCluedFound:()->Unit,
               modifier: Modifier = Modifier
){
    val uiState by thViewModel.uiState.collectAsState()
    val timeValue by timerViewModel.timer.collectAsState()
    timerViewModel.startTimer()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text= stringResource(com.example.treasure_hunt_fixed.R.string.clue) + uiState.currentClue + "/3",
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
    ) {innerPadding->
        Column(modifier.padding(innerPadding )){

            //spacer to put some room
            Spacer(modifier=modifier.height(100.dp))
            //timer box
            Box(modifier = modifier
                .padding(start=10.dp,end=10.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()){
                Text(text=timeValue.formatTime(),
                    fontSize = 90.sp,
                    fontWeight = FontWeight.Bold,
                    modifier=modifier.align(Alignment.Center))
            }

            Text(text=stringResource(com.example.treasure_hunt_fixed.R.string.placeholder_clue),
                modifier=modifier.padding(top=50.dp,start=10.dp,end=10.dp))


            Box(modifier=modifier
                .padding(top=64.dp,start=10.dp,end=10.dp)
                .height(200.dp)){
                if(!uiState.isHintRevealed){


                    Button(onClick = {thViewModel.revealHint()},
                        modifier=modifier
                            .fillMaxWidth()
                            .padding(top=50.dp, bottom = 50.dp)) {
                        Text(text=stringResource(com.example.treasure_hunt_fixed.R.string.reveal_hint),
                            fontWeight = FontWeight.SemiBold)
                    }

                    Spacer(modifier.height(50.dp))

                }else{
                    //display hint text
                    Text(text=stringResource(com.example.treasure_hunt_fixed.R.string.placeholder_hint))
                }

            }

            Button(onClick = onFoundButtonClicked,
                modifier=modifier
                    .fillMaxWidth()
                    .padding(start=10.dp,end=10.dp)){
                Text(text=stringResource(com.example.treasure_hunt_fixed.R.string.found_it), fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

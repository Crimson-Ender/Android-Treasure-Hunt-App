package com.example.treasure_hunt_fixed.ui

import android.Manifest
import com.example.treasure_hunt_fixed.R
import android.adservices.topics.Topic
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.location.Location
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.treasurehunt.model.THViewModel
import com.google.android.gms.location.Priority
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.treasure_hunt_fixed.model.TimerViewModel
import kotlinx.coroutines.flow.combine

@SuppressLint("DefaultLocale")
fun Long.formatTime():String {
    //format the timer output so it isn't just one giant integer of just seconds counting up

    val hours = this/3600
    val minutes = (this %3600)/60
    val remainingSeconds = this%60

    return String.format("%02d:%02d:%02d",hours,minutes,remainingSeconds)
}

@SuppressLint("ContextCastToActivity")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClueScreen(thViewModel: THViewModel = viewModel(),
               timerViewModel: TimerViewModel=viewModel(),
               onFoundButtonClicked: ()->Unit,
               onFinalCluedFound:()->Unit,
               modifier: Modifier = Modifier
){
    val activity = (LocalContext.current as Activity)
    val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)

    val uiState by thViewModel.uiState.collectAsState()
    val timeValue by timerViewModel.timer.collectAsState()
    timerViewModel.startTimer()

    var location = remember { mutableStateOf<Location?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text= stringResource(R.string.clue) + uiState.currentClue + "/3",
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

            //Text(text="Debug: " + stringResource(uiState.currentLocation.name))
            //spacer to put some room
            Spacer(modifier=modifier.height(70.dp))
            //timer box
            Box(modifier = modifier
                .padding(start = 10.dp, end = 10.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()){
                Text(text=timeValue.formatTime(),
                    fontSize = 90.sp,
                    fontWeight = FontWeight.Bold,
                    modifier=modifier.align(Alignment.Center))
            }

            Text(text=stringResource(uiState.currentLocation.clue),
                modifier=modifier.padding(top=50.dp,start=10.dp,end=10.dp))


            Box(modifier=modifier
                .padding(top = 64.dp, start = 10.dp, end = 10.dp)
                .height(200.dp)){
                if(!uiState.isHintRevealed){


                    Button(onClick = {thViewModel.revealHint()},
                        modifier=modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp, bottom = 50.dp)) {
                        Text(text=stringResource(R.string.reveal_hint),
                            fontWeight = FontWeight.SemiBold)
                    }

                    Spacer(modifier.height(50.dp))

                }else{
                    //display hint text
                    Text(text=stringResource(uiState.currentLocation.hint))
                }

            }

            Button(onClick = {

                if(ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    fusedLocationClient.getCurrentLocation(
                        Priority.PRIORITY_HIGH_ACCURACY,null
                    ).addOnSuccessListener {loc: Location?->
                        location.value = loc

                         thViewModel.checkLocationGuess(loc)
                        Log.d("TEST", "isCorrect = ${uiState.isCorrect}")

                        if(thViewModel.uiState.value.isCorrect){
                            //go to new next clue page
                            if(uiState.currentClue!=3){
                                onFoundButtonClicked()
                            }else{
                                onFinalCluedFound()
                            }

                        }else{
                            //go to wrong guess page
                            showAlertDialog(activity)

                        }

                    }
                }


            },
                modifier=modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)){
                Text(text=stringResource(R.string.found_it), fontWeight = FontWeight.SemiBold)
            }
            //Text(text="DEBUG: CURRENT LOCATION LONG: "+uiState.currentLocation.long + " CURRENT LOCATION LAT: "+uiState.currentLocation.lat)
            //Text(text="DEBUG: LONG: "+ location.value?.longitude + " LAT: "+location.value?.latitude)
            //Text(text="DEBUG: Distance == " + uiState.debugDistDiff)
        }
    }
}


fun showAlertDialog(context: Context){
    AlertDialog.Builder(context)
        .setMessage("Incorrect guess! Try again!")
        .setNeutralButton("Dimiss"){dialog,which->
            dialog.dismiss()
        }
        .show()
}
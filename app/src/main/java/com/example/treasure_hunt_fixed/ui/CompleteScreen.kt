package com.example.treasure_hunt_fixed.ui

import androidx.compose.runtime.Composable
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.location.Location
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.treasure_hunt_fixed.R
import com.example.treasure_hunt_fixed.model.TimerViewModel
import com.example.treasurehunt.model.THViewModel
import com.google.android.gms.location.Priority

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteScreen(thViewModel: THViewModel = viewModel(),
                   timerViewModel: TimerViewModel=viewModel(),
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
}
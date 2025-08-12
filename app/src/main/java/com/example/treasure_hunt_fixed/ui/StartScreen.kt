/*
Author: Max Baker
Email: bakerm7@oregonstate.edu
OSU CS 492 - Mobile App Development
Final Project: Treasure Hunt
StartScreen.kt
 */


package com.example.treasure_hunt_fixed.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.treasurehunt.model.THViewModel
import com.example.treasure_hunt_fixed.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.android.gms.location.Priority

@Composable
fun StartScreen(
    thViewModel: THViewModel = viewModel(),
    onStartButtonClicked:()->Unit,
    modifier: Modifier = Modifier){

    val uiState by thViewModel.uiState.collectAsState()

    //column that contains the UI elements for this page
    Column(modifier=modifier.padding(top=150.dp, bottom=50.dp)) {
        //main title for the app
        Box(modifier=modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(/*top=50.dp*/)){
            Text(text=stringResource(R.string.title),
                textAlign= TextAlign.Center,
                fontSize= 80.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 80.sp,
                modifier=modifier
                    .align(alignment = Alignment.Center))
        }

        //start button
        Button(onClick = {onStartButtonClicked()},
            modifier=modifier
                .padding(start=8.dp,end=8.dp,top=48.dp)
                .fillMaxWidth()
                .height(60.dp)){
            Text(text=stringResource(R.string.start_button), fontWeight = FontWeight.SemiBold, fontSize = 30.sp)
        }

        Box(modifier=modifier
            .padding(top=30.dp, start = 8.dp, end = 8.dp)
            .height(240.dp)
            .background(color = Color.LightGray)
            .fillMaxSize()){
            Column {
                Text(text=stringResource(R.string.rules_title),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = modifier
                        .padding(bottom = 20.dp)
                        )

                val scrollState = rememberScrollState()
                Text(text=stringResource(R.string.rules),
                    lineHeight = 24.sp,
                    fontSize = 24.sp,
                    modifier = modifier
                        .verticalScroll(scrollState)
                )
            }

        }
    }
}

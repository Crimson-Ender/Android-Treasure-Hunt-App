package com.example.treasure_hunt_fixed.ui

import android.adservices.topics.Topic
import androidx.compose.runtime.Composable
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.location.Location
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.treasurehunt.model.THViewModel
import com.google.android.gms.location.Priority
@Composable
fun ClueScreen(thViewModel: THViewModel = viewModel(),
               onFoundButtonClicked: ()->Unit
){

}
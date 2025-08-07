package com.example.treasure_hunt_fixed.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun StartScreen(
    thViewModel: THViewModel = viewModel(),
    onStartButtonClicked:()->Unit,
    modifier: Modifier = Modifier){

    val uiState by thViewModel.uiState.collectAsState()

    //column that contains the UI elements for this page
    Column(modifier=modifier.padding(10.dp)) {
        Box(modifier=modifier.fillMaxWidth().height(120.dp)){
            Text(text=stringResource(R.string.app_name))
        }

        Button(onClick = {onStartButtonClicked()},
            modifier=modifier.padding(start=4.dp,end=4.dp).fillMaxSize()){
            Text(text=stringResource(R.string.start_button))
        }
    }
}
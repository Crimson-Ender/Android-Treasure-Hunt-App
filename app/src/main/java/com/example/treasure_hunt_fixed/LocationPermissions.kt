/*
Author: Max Baker
Email: bakerm7@oregonstate.edu
OSU CS 492 - Mobile App Development
Final Project: Treasure Hunt
LocationPermissions.kt
 */


package com.example.treasure_hunt_fixed

import android.Manifest
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.treasurehunt.model.THViewModel
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext


/*
Handles the app asking for permissions on startup. Presents the user with a pop-up prompt and asks them to accept or reject location permissions.
If the user accepts location permissions,
 */
@Composable
fun RequestLocationPermission(viewModel: THViewModel= viewModel()){
    val uiState by viewModel.uiState.collectAsState()

    //precise location vs general location
    val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    val context = LocalContext.current
    val activity = context as? Activity

    val permissionsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) {permissionsMap->
        val usingFine = permissionsMap[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val usingCoarse = permissionsMap[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
        //update permissions in the UI State class
        viewModel.onPermissionsChange(usingFine,usingCoarse)

        //if the user did not accept BOTH fine and coarse location permissions, the app will automatically close itself.
        if(!usingFine && !usingCoarse){
            activity?.finish()
        }
    }

    var showDialog by remember { mutableStateOf(!uiState.permissionsGranted) }

    //present the user with a prompt to allow or deny location permissions
    if(showDialog){
        AlertDialog(
            onDismissRequest = {showDialog=false
                activity?.finish()},
            title ={Text("Location permissions required!")},
            text = {Text("This app needs access to your device's location for essential functions")},
            confirmButton = {
                TextButton(
                    onClick = {
                        //if the user accepts permissions, close the dialog and continue with the app
                        showDialog = false
                        permissionsLauncher.launch(permissions)
                    }
                ) {
                    Text("Allow Access")}
                },
            dismissButton = {
                //if the user rejects permissions, automatically close the app
                TextButton(onClick = {showDialog=false
                activity?.finish()})
                {Text("Deny Access") }
            }

        )
    }
}
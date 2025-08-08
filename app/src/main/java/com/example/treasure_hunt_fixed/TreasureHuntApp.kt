package com.example.treasure_hunt_fixed

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.treasure_hunt_fixed.ui.ClueScreen
import com.example.treasure_hunt_fixed.ui.CompleteScreen
import com.example.treasure_hunt_fixed.ui.NextClueScreen
import com.example.treasure_hunt_fixed.ui.StartScreen
import com.example.treasurehunt.model.THViewModel

enum class Screens(){
    Start,Clue,NextClue,Complete
}

private val TAG = "TreasureHuntApp"

@Composable
fun TreasureHuntApp(
    viewModel: THViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Log.d(TAG, "App Starting")

    //request permissions here

    NavHost(
        navController = navController,
        startDestination = Screens.Start.name,
        modifier = Modifier.fillMaxSize().padding()
    ) {
        //navigate to the start screen
        composable(route = Screens.Start.name) {
            StartScreen(onStartButtonClicked = {/**/})
        }
        //navigate to the clue screen
        composable(route = Screens.Clue.name) {
            ClueScreen()
        }
        //navigate to the next clue screen
        composable(route = Screens.NextClue.name) {
            NextClueScreen()
        }
        //navigate to the completion screen
        composable(route = Screens.Complete.name) {
            CompleteScreen()
        }

    }
}

@Composable
fun PermissionsDialog(){

}
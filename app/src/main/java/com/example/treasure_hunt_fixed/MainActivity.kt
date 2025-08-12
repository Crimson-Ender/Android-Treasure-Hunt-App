/*
Author: Max Baker
Email: bakerm7@oregonstate.edu
OSU CS 492 - Mobile App Development
Final Project: Treasure Hunt
MainActivity.kt
 */

package com.example.treasure_hunt_fixed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.treasure_hunt_fixed.ui.theme.TreasureHuntFixedTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TreasureHuntFixedTheme {
               TreasureHuntApp()
                }
            }
        }
    }
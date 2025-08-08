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
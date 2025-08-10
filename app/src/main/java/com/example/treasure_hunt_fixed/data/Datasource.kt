package com.example.treasure_hunt_fixed.data

import com.example.treasure_hunt_fixed.R
import androidx.compose.ui.res.stringResource

class Datasource() {

    fun loadLocations(): List<Location>{
        return listOf<Location>(
            Location(name=R.string.tebeau_name,
                clue=R.string.tebeau_clue,
                hint=R.string.tebeau_hint,
                trivia =R.string.tebeau_trivia,
                long=-123.2729333,
                lat=44.563125),
            Location(name=R.string.rose_name,
                clue=R.string.rose_clue,
                hint=R.string.rose_hint,
                trivia =R.string.rose_trivia,
                long=-123.273291,
                lat=44.555136),
            Location(name=R.string.train_name,
                clue=R.string.train_clue,
                hint=R.string.train_hint,
                trivia =R.string.train_trivia,
                long=-123.270061,
                lat=44.553272)
        )
    }
}
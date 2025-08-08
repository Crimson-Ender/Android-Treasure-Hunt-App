package com.example.treasure_hunt_fixed.model

data class PermissionRationaleState(
    val title: Int,
    val rationale: Int,
    val onRationaleReply:(proceed:Boolean)->Unit
)

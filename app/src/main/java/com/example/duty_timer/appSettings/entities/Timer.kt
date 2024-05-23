package com.example.duty_timer.appSettings.entities

import java.io.Serializable
import java.util.Date

// TODO: Change it to parcelable
data class Timer(
    val id: Int,
    val startTime: Long,
    val endTime: Long
)  : Serializable

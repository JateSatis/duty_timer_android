package com.example.duty_timer.appSettings.entities

import java.io.Serializable
import java.util.Date

//TODO: Change it to parcelable
interface Timer {
    val startTime: Long
    val endTime: Long
}

data class LocalTimer(override val startTime: Long, override val endTime: Long) : Timer, Serializable
data class ServerTimer(val id: Int, override val startTime: Long, override val endTime: Long) : Timer, Serializable

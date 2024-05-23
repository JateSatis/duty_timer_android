package com.example.duty_timer.model.timer.entities

import java.util.Date

data class ConnectToTimerResponseBody(
    val id: Int,
    val startTime: Long,
    val endTime: Long
)
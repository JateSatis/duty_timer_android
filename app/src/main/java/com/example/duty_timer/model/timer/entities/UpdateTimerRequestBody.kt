package com.example.duty_timer.model.timer.entities

import java.util.Date

data class UpdateTimerRequestBody(
    val startTimer: Long,
    val endTime: Long,
)
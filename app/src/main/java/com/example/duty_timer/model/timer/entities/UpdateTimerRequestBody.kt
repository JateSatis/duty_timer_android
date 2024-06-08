package com.example.duty_timer.model.timer.entities

import java.util.Date

data class UpdateTimerRequestBody(
    val start_time: Long,
    val end_time: Long,
)
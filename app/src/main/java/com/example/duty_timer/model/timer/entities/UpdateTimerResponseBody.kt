package com.example.duty_timer.model.timer.entities

data class UpdateTimerResponseBody(
    val id: Int,
    val start_time: Long,
    val end_time: Long
)
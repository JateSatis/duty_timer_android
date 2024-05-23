package com.example.duty_timer.model.timer

import com.example.duty_timer.model.timer.entities.ConnectToTimerResponseBody
import com.example.duty_timer.model.timer.entities.GetTimerResponseBody
import com.example.duty_timer.model.timer.entities.UpdateTimerResponseBody
import java.util.Date

interface TimerSource {
    suspend fun getTimer(token: String): GetTimerResponseBody

    suspend fun updateTimer(token: String, startTime: Long, endTime: Long) : UpdateTimerResponseBody

    suspend fun connectToTimer(token: String, timerId: Int) : ConnectToTimerResponseBody
}
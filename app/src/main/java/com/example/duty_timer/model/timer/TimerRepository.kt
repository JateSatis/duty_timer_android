package com.example.duty_timer.model.timer

import com.example.duty_timer.appSettings.AppSettings
import com.example.duty_timer.appSettings.entities.Timer
import com.example.duty_timer.utils.EmptyTask
import com.example.duty_timer.utils.ResultTask
import com.example.duty_timer.utils.SuccessTask
import kotlinx.coroutines.flow.MutableStateFlow

class TimerRepository(
    private val timerSource: TimerSource,
    private val appSettings: AppSettings
) {

    private val _timerFlow = MutableStateFlow<ResultTask<Timer>>(EmptyTask())
    val timerFlow = _timerFlow
    private val token = appSettings.getCurrentToken()
    private val savedTimer = appSettings.getCurrentTimer()

    suspend fun getTimer() {
        if (savedTimer != null) {
            _timerFlow.value = SuccessTask(savedTimer)
        }
        if (token != null) {
            val getTimerResponseBody = timerSource.getTimer(token);
            val timer = Timer(getTimerResponseBody.id, getTimerResponseBody.start_time, getTimerResponseBody.end_time)
            appSettings.setCurrentTimer(timer)
            _timerFlow.value = SuccessTask(timer)
        }
    }

    suspend fun updateTimer(startTime: Long, endTime: Long) {
        if (token != null) {
            val updateTimerResponseBody = timerSource.updateTimer(token, startTime, endTime);
            val timer = Timer(updateTimerResponseBody.id, updateTimerResponseBody.startTime, updateTimerResponseBody.endTime)
            _timerFlow.value = SuccessTask(timer)
        }
    }

    suspend fun connectToTimer(timerId: Int) {
        if (token != null) {
            val connectToTimerResponseBody = timerSource.connectToTimer(token, timerId)
            val timer = Timer(connectToTimerResponseBody.id, connectToTimerResponseBody.startTime, connectToTimerResponseBody.endTime)
            _timerFlow.value = SuccessTask(timer)
        }
    }
}
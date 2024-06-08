package com.example.duty_timer.model.timer

import android.util.Log
import com.example.duty_timer.appSettings.AppSettings
import com.example.duty_timer.appSettings.entities.LocalTimer
import com.example.duty_timer.appSettings.entities.ServerTimer
import com.example.duty_timer.appSettings.entities.Timer
import com.example.duty_timer.globals.Singletons
import com.example.duty_timer.utils.EmptyTask
import com.example.duty_timer.utils.ResultTask
import com.example.duty_timer.utils.SuccessTask
import kotlinx.coroutines.flow.MutableStateFlow

class TimerRepository(
    private val timerSource: TimerSource,
    private val appSettings: AppSettings
) {

    private val _timerResultFlow = MutableStateFlow<ResultTask<Timer>>(EmptyTask())
    val timerResultFlow = _timerResultFlow

    suspend fun getTimer() {
        val token = appSettings.getCurrentToken()
        val savedTimer = appSettings.getCurrentTimer()
        if (token != null) {
            val getTimerResponseBody = timerSource.getTimer(token);
            val timer = ServerTimer(getTimerResponseBody.id, getTimerResponseBody.start_time, getTimerResponseBody.end_time)
            appSettings.setCurrentTimer(timer)
            _timerResultFlow.value = SuccessTask(timer)
        }
        else if (savedTimer != null) {
            _timerResultFlow.value = SuccessTask(savedTimer)
        }
    }

    suspend fun updateTimer(startTime: Long, endTime: Long) {
        val token = appSettings.getCurrentToken()
        if (token != null) {
            Log.d("TIMER_AAAA", "wtf")
            val updateTimerResponseBody = timerSource.updateTimer(token, startTime, endTime);
            val timer = ServerTimer(updateTimerResponseBody.id, updateTimerResponseBody.start_time, updateTimerResponseBody.start_time)
            _timerResultFlow.value = SuccessTask(timer)
        }
        else {
            appSettings.setCurrentTimer(LocalTimer(startTime, endTime))
        }
    }

    suspend fun connectToTimer(timerId: Int) {
        val token = appSettings.getCurrentToken()
        if (token != null) {
            val connectToTimerResponseBody = timerSource.connectToTimer(token, timerId)
            val timer = ServerTimer(connectToTimerResponseBody.id, connectToTimerResponseBody.startTime, connectToTimerResponseBody.endTime)
            _timerResultFlow.value = SuccessTask(timer)
        }
    }
}
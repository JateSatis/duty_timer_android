package com.example.duty_timer.screens.timer

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duty_timer.appSettings.entities.Timer
import com.example.duty_timer.globals.Singletons
import com.example.duty_timer.model.auth.AuthRepository
import com.example.duty_timer.model.timer.TimerRepository
import com.example.duty_timer.utils.MutableLiveResult
import com.example.duty_timer.utils.SuccessTask
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import kotlin.concurrent.fixedRateTimer
import java.util.Date
import kotlin.math.roundToLong

class TimerViewModel(
    private val timerRepository: TimerRepository = Singletons.timerRepository,
) : ViewModel() {
    private val _timerResult = MutableLiveResult<Timer>()
    val timerResult = _timerResult

    private val _percentage = MutableLiveData<String>()
    val percentage = _percentage


    private var fixedRateTimer: java.util.Timer? = null
    private val decimalFormat = DecimalFormat("#.######")
    private val decimalPoint = 1.0 / 1000000

    init {
        viewModelScope.launch {
            timerRepository.timerResultFlow.collect {
                _timerResult.value = it

                if (fixedRateTimer == null && it is SuccessTask) {
                    val timer = it.value;
                    val currentTime = Date()

                    Log.d("timer", "${timer.startTime} ${timer.endTime}")

                    val totalDuration = timer.endTime - timer.startTime
                    val passedDuration = currentTime.time - timer.startTime
                    val percentagePassed = (passedDuration.toDouble() / totalDuration) * 100
                    val onePercentTime = totalDuration / 100
                    val period = onePercentTime.toDouble() * decimalPoint

                    _percentage.value = decimalFormat.format(percentagePassed)

                    fixedRateTimer = fixedRateTimer("PercentageCalculator", initialDelay = 0, period = period.roundToLong()) {
                        val newPercent = (percentage.value?.toDoubleOrNull() ?: 0.0) + decimalPoint
                        val formattedPercent = decimalFormat.format(newPercent)
                        _percentage.postValue(formattedPercent)
                    }
                }
            }
        }
    }

    fun getTimer() {
        viewModelScope.launch {
            timerRepository.getTimer()
        }
    }

    fun updateTimer(startTime: Long, endTime: Long) {
        viewModelScope.launch {
            timerRepository.updateTimer(startTime, endTime)
        }
    }

    fun connectToTimer(timerId: Int) {
        viewModelScope.launch {
            timerRepository.connectToTimer(timerId)
        }
    }

    fun clearFixedRateTimer() {
        fixedRateTimer?.cancel()
        fixedRateTimer = null
    }
}
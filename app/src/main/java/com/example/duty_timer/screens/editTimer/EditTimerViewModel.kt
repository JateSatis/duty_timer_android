package com.example.duty_timer.screens.editTimer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duty_timer.appSettings.entities.Timer
import com.example.duty_timer.globals.Singletons
import com.example.duty_timer.model.timer.TimerRepository
import com.example.duty_timer.utils.MutableLiveResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EditTimerViewModel(
    private val timerRepository: TimerRepository = Singletons.timerRepository
) : ViewModel() {

    private val _timerResult = MutableLiveResult<Timer>()
    val timeResult = _timerResult

    init {
        viewModelScope.launch {
            timerRepository.timerResultFlow.collect {
                _timerResult.value = it
            }
        }
    }

    fun getCurrentTimer() {
        viewModelScope.launch {
            timerRepository.getTimer()
        }
    }

    fun updateTimer(startTime: Long, endTime: Long) {
        viewModelScope.launch {
            timerRepository.updateTimer(startTime, endTime)
        }
    }

}
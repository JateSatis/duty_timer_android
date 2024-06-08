package com.example.duty_timer.screens.newSoldier

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duty_timer.model.RepositoriesProvider
import com.example.duty_timer.model.timer.TimerRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewSoldierViewModel(
    private val timerRepository: TimerRepository = RepositoriesProvider.timerRepository
) : ViewModel() {

    fun saveTimer(startTime: Long, endTime: Long) {
        viewModelScope.launch {
            timerRepository.updateTimer(startTime, endTime)
        }
    }

}
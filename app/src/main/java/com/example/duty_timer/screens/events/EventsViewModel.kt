package com.example.duty_timer.screens.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duty_timer.globals.Singletons
import com.example.duty_timer.model.events.EventsRepository
import com.example.duty_timer.model.events.entities.EventEntity
import com.example.duty_timer.utils.MutableLiveResult
import com.example.duty_timer.utils.SuccessTask
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EventsViewModel(
    private val eventsRepository: EventsRepository = Singletons.eventsRepository
) : ViewModel() {

    private val _eventsResult = MutableLiveResult<List<EventEntity>?>()
    val eventsResult = _eventsResult

    init {
        viewModelScope.launch {
            eventsRepository.eventsResultFlow.collect {
                _eventsResult.value = it
            }
        }
    }

    fun getAllEvents() {
        viewModelScope.launch {
            eventsRepository.getAllEvents()
        }
    }

    fun addEvent(title: String, millis: Long) {
        viewModelScope.launch {
            eventsRepository.addEvent(title, millis)
        }
    }

    fun deleteEvent(eventId: Int) {
        viewModelScope.launch {
            eventsRepository.deleteEvent(eventId)
        }
    }

}
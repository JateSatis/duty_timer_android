package com.example.duty_timer.model.events

import com.example.duty_timer.appSettings.AppSettings
import com.example.duty_timer.model.events.entities.EventEntity
import com.example.duty_timer.utils.EmptyTask
import com.example.duty_timer.utils.PendingTask
import com.example.duty_timer.utils.ResultTask
import com.example.duty_timer.utils.SuccessTask
import kotlinx.coroutines.flow.MutableStateFlow

class EventsRepository(
    private val eventsSource: EventsSource,
    private val appSettings: AppSettings
) {

    private val _eventsResultFlow = MutableStateFlow<ResultTask<List<EventEntity>?>>(EmptyTask())
    val eventsResultFlow = _eventsResultFlow

    suspend fun getAllEvents() {
        _eventsResultFlow.value = PendingTask()
        val token = appSettings.getCurrentToken() ?: return
        val events = eventsSource.getAllEvents(token)
        _eventsResultFlow.value = SuccessTask(events)
    }

    suspend fun addEvent(title: String, millis: Long) {
        _eventsResultFlow.value = PendingTask()
        val token = appSettings.getCurrentToken() ?: return
        val events = eventsSource.addNewEvent(token, title, millis)
        _eventsResultFlow.value = SuccessTask(events)
    }

    suspend fun deleteEvent(eventId: Int) {
        _eventsResultFlow.value = PendingTask()
        val token = appSettings.getCurrentToken() ?: return
        val events = eventsSource.deleteEvent(token, eventId)
        _eventsResultFlow.value = SuccessTask(events)
    }

}
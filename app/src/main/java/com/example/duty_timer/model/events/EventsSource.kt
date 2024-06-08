package com.example.duty_timer.model.events

import com.example.duty_timer.model.events.entities.EventEntity

interface EventsSource {

    suspend fun getAllEvents(token: String) : List<EventEntity>

    suspend fun addNewEvent(token: String, title: String, millis: Long) : List<EventEntity>

    suspend fun deleteEvent(token: String, eventId: Int) : List<EventEntity>

}
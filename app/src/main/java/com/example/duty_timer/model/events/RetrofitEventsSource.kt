package com.example.duty_timer.model.events

import com.example.duty_timer.model.events.entities.AddEventRequestEntity
import com.example.duty_timer.model.events.entities.EventEntity
import com.example.duty_timer.model.sourcesBase.BaseRetrofitSource
import com.example.duty_timer.model.sourcesBase.RetrofitConfig

class RetrofitEventsSource(config: RetrofitConfig) : EventsSource, BaseRetrofitSource(config) {

    private val eventsApi = retrofit.create(EventsApi::class.java)

    override suspend fun getAllEvents(token: String): List<EventEntity> = wrapRetrofitExceptions {
        eventsApi.getAllTimers(token)
    }

    override suspend fun addNewEvent(token: String, title: String, millis: Long): List<EventEntity> = wrapRetrofitExceptions {
        eventsApi.addEvent(token, AddEventRequestEntity(title, millis))
    }

    override suspend fun deleteEvent(token: String, eventId: Int) : List<EventEntity> = wrapRetrofitExceptions {
        eventsApi.deleteEvent(token, eventId)
    }
}
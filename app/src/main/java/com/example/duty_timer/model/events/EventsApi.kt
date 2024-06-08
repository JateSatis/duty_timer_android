package com.example.duty_timer.model.events

import com.example.duty_timer.model.events.entities.AddEventRequestEntity
import com.example.duty_timer.model.events.entities.EventEntity
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface EventsApi {

    @GET("/event/")
    suspend fun getAllTimers(@Header("Authorization") token: String) : List<EventEntity>

    @POST("/event/")
    suspend fun addEvent(@Header("Authorization") token: String, @Body addEventRequestEntity: AddEventRequestEntity) : List<EventEntity>

    @DELETE("/event/{eventId}")
    suspend fun deleteEvent(@Header("Authorization") token: String, @Path("eventId") eventId: Int) : List<EventEntity>

}
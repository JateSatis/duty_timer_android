package com.example.duty_timer.model.events.entities

data class AddEventRequestEntity(
    val title: String,
    val millis: Long
)
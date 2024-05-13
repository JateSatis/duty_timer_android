package com.example.duty_timer.model.auth.entities

data class SignUpResponseEntity(
    val expiresIn: String,
    val token: String
)
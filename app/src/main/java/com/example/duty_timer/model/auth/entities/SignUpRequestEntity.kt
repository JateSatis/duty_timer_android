package com.example.duty_timer.model.auth.entities

data class SignUpRequestEntity (
    val email: String,
    val password: String,
    val name: String,
    val surname: String,
    val nickname: String,
)
package com.example.duty_timer.model.auth.entities

data class SignUpRequestEntity (
    val login: String,
    val password: String,
    val name: String,
    val nickname: String,
//    val userType: String
)
package com.example.duty_timer.model.auth

interface AuthSource {
    suspend fun singIn(email: String, password: String) : String

    suspend fun singUp(email: String, password: String, name: String, surname: String, nickname: String) : String

    suspend fun logOut(token: String)
}
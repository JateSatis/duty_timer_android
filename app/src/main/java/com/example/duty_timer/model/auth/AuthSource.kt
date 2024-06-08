package com.example.duty_timer.model.auth

import com.example.duty_timer.appSettings.UserType

interface AuthSource {
    suspend fun singIn(email: String, password: String) : String

    suspend fun singUp(email: String, password: String, name: String, nickname: String) : String

    suspend fun logOut(token: String)
}
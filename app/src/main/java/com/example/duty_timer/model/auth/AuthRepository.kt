package com.example.duty_timer.model.auth

import com.example.duty_timer.BackendException
import com.example.duty_timer.InvalidCredentialsException
import com.example.duty_timer.appSettings.AppSettings

class AuthRepository(
    private val authSource : AuthSource,
    private val appSettings : AppSettings
) {
    suspend fun signIn(email: String, password: String) {
        val token = try {
            authSource.singIn(email, password)
        } catch (e: Exception) {
            if (e is BackendException && e.code == 401) {
                // map 401 error for sign-in to InvalidCredentialsException
                throw InvalidCredentialsException(e)
            } else {
                throw e
            }
        }

        appSettings.setCurrentToken(token)
    }

    suspend fun logOut() {
        val token = appSettings.getCurrentToken() ?: return
        appSettings.setCurrentToken(null);
        authSource.logOut(token)
    }
}
package com.example.duty_timer.model.auth

import android.util.Log
import com.example.duty_timer.BackendException
import com.example.duty_timer.InvalidCredentialsException
import com.example.duty_timer.appSettings.AppSettings
import com.example.duty_timer.appSettings.UserType
import com.example.duty_timer.globals.Singletons
import com.example.duty_timer.model.timer.TimerRepository
import com.example.duty_timer.model.timer.TimerSource
import com.example.duty_timer.utils.SuccessTask
import kotlinx.coroutines.flow.collect

class AuthRepository(
    private val authSource : AuthSource,
    private val timerSource: TimerSource,
    private val appSettings : AppSettings,
) {

    suspend fun signUp(email: String, password: String, name: String, nickname: String) {
        val token = try {
            authSource.singUp(email, password, name, nickname)
        } catch (e: Exception) {
            if (e is BackendException && e.code == 401) {
                // map 401 error for sign-in to InvalidCredentialsException
                throw InvalidCredentialsException(e)
            } else {
                throw e
            }
        }

        try {
            val timer = appSettings.getCurrentTimer()
            timerSource.updateTimer(token, timer?.startTime!!, timer.endTime)
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
        appSettings.setCurrentTimer(null);
        authSource.logOut(token)
    }
}
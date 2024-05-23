package com.example.duty_timer.appSettings

import com.example.duty_timer.appSettings.entities.Timer
import java.util.Date

interface AppSettings {
    /**
     * Get auth token of the current logged-in user.
     */
    fun getCurrentToken(): String?

    /**
     * Set auth token of the logged-in user.
     */
    fun setCurrentToken(token: String?)

    fun getCurrentTimer() : Timer?
    fun setCurrentTimer(timer: Timer)
}
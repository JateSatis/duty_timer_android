package com.example.duty_timer.appSettings

import com.example.duty_timer.appSettings.entities.Timer
import java.util.Date

enum class UserType {
    SOLDIER,
    OFFICER,
    CADET,
    RELATIVE
}

interface AppSettings {
    /**
     * Get auth token of the current logged-in user.
     */

//    var userType: UserType?

    fun setCurrentToken(token: String?)
    fun getCurrentToken() : String?

    fun setCurrentTimer(timer: Timer?)
    fun getCurrentTimer() : Timer?

}
package com.example.duty_timer.appSettings

import android.content.Context
import android.util.Log
import com.example.duty_timer.appSettings.entities.LocalTimer
import com.example.duty_timer.appSettings.entities.Timer
import com.google.gson.Gson
import java.util.Date

class SharedPreferencesAppSettings(appContext: Context) : AppSettings {

    private val sharedPreferences = appContext.getSharedPreferences("settings", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()
    private val gson = Gson()

    override fun getCurrentToken(): String? {
        return sharedPreferences.getString(ACCOUNT_TOKEN, null)
    }

    override fun setCurrentToken(token: String?) {
        if (token == null)
            editor.putString(ACCOUNT_TOKEN, null)
        else
            editor.putString(ACCOUNT_TOKEN, token)
        editor.apply()
    }

    override fun getCurrentTimer(): Timer? {
        val timeJson = sharedPreferences.getString(TIMER, null)
        return gson.fromJson(timeJson, LocalTimer::class.java)
    }

    override fun setCurrentTimer(timer: Timer?) {
        if (timer == null) {
            editor.putString(TIMER, null)
        }
        else {
            val timerJson = gson.toJson(timer)
            editor.putString(TIMER, timerJson)
        }
        editor.apply()
    }

//    override var userType: UserType? = UserType.SOLDIER
//        get() {
//            val userTypeString = sharedPreferences.getString(USER_TYPE, null) ?: return null
//            return UserType.valueOf(userTypeString)
//        }
//        set(userType) {
//            userType?.let { editor.putString(USER_TYPE, userType.name) }
//            field = userType
//        }


    companion object {

        private const val ACCOUNT_TOKEN = "ACCOUNT_TOKEN"
        private const val TIMER = "TIMER"
        private const val USER_TYPE = "USER_TYPE"
    }
}
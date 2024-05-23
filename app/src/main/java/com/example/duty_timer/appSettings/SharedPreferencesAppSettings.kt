package com.example.duty_timer.appSettings

import android.content.Context
import com.example.duty_timer.appSettings.entities.Timer
import com.google.gson.Gson
import java.util.Date

/**
 * Implementation of [AppSettings] based on [SharedPreferences].
 */
class SharedPreferencesAppSettings(appContext: Context) : AppSettings {

    private val sharedPreferences = appContext.getSharedPreferences("settings", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()
    private val gson = Gson()

    override fun getCurrentToken(): String? =
        sharedPreferences.getString(ACCOUNT_TOKEN, null)

    override fun setCurrentToken(token: String?) {
        if (token == null)
            editor.remove(ACCOUNT_TOKEN)
        else
            editor.putString(ACCOUNT_TOKEN, token)
        editor.apply()
    }

    override fun getCurrentTimer(): Timer? {
        val timeJson = sharedPreferences.getString(TIMER, null) ?: return null
        return gson.fromJson(timeJson, Timer::class.java)
    }

    override fun setCurrentTimer(timer: Timer) {
        val timerJson = gson.toJson(timer)
        editor.putString(TIMER, timerJson)
    }



    companion object {
        private const val ACCOUNT_TOKEN = "ACCOUNT_TOKEN"
        private const val TIMER = "TIMER"
    }
}
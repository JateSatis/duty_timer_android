package com.example.duty_timer.globals

import android.content.Context
import com.example.duty_timer.model.auth.AuthRepository
import com.example.duty_timer.appSettings.AppSettings
import com.example.duty_timer.appSettings.SharedPreferencesAppSettings
import com.example.duty_timer.model.RepositoriesProvider
import com.example.duty_timer.model.userInfo.UserInfoRepository

object Singletons {

    private lateinit var appContext: Context

    // --- repositories

    val authRepository by lazy {
        RepositoriesProvider.authRepository
    }

    val userInfoRepository by lazy {
        RepositoriesProvider.userInfoRepository
    }

    val timerRepository by lazy {
        RepositoriesProvider.timerRepository
    }

    val eventsRepository by lazy {
        RepositoriesProvider.eventsRepository
    }

    // --- app settings

    val appSettings: AppSettings by lazy {
        SharedPreferencesAppSettings(appContext)
    }

    /**
     * Call this method in all application components that may be created at app startup/restoring
     * (e.g. in onCreate of activities and services)
     */
    fun init(appContext: Context) {
        Singletons.appContext = appContext
    }
}
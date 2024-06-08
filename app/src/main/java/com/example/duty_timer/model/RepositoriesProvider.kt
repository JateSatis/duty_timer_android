package com.example.duty_timer.model

import com.example.duty_timer.globals.Singletons
import com.example.duty_timer.model.userInfo.UserInfoRepository
import com.example.duty_timer.model.auth.AuthRepository
import com.example.duty_timer.model.events.EventsRepository
import com.example.duty_timer.model.sourcesBase.providers.RetrofitSourcesProvider
import com.example.duty_timer.model.sourcesBase.providers.SourcesProvider
import com.example.duty_timer.model.timer.TimerRepository

object RepositoriesProvider {

    private val sourcesProvider: SourcesProvider by lazy {
        RetrofitSourcesProvider
    }
    private val appSettings = Singletons.appSettings

    val authRepository by lazy {
        AuthRepository(sourcesProvider.authSource,  sourcesProvider.timerSource, appSettings)
    }

    val userInfoRepository by lazy {
        UserInfoRepository(sourcesProvider.userInfoSource, appSettings)
    }

    val timerRepository by lazy {
        TimerRepository(sourcesProvider.timerSource, appSettings)
    }

    val eventsRepository by lazy {
        EventsRepository(sourcesProvider.eventsSource, appSettings)
    }

}
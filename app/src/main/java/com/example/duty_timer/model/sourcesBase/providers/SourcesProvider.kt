package com.example.duty_timer.model.sourcesBase.providers

import com.example.duty_timer.model.userInfo.UserInfoSource
import com.example.duty_timer.model.auth.AuthSource
import com.example.duty_timer.model.timer.TimerSource

interface SourcesProvider {

    val authSource: AuthSource
    val userInfoSource: UserInfoSource
    val timerSource: TimerSource

}
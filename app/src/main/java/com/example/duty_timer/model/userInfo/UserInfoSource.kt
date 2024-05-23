package com.example.duty_timer.model.userInfo

import com.example.duty_timer.model.userInfo.entitites.UserInfo

interface UserInfoSource {

    suspend fun getUserInfo(token: String) : UserInfo

}
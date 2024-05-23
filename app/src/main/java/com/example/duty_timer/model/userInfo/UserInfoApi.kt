package com.example.duty_timer.model.userInfo

import com.example.duty_timer.model.userInfo.entitites.UserInfo
import retrofit2.http.GET
import retrofit2.http.Header

interface UserInfoApi {
    @GET("/user")
    suspend fun getUserInfo(@Header("Authorization") token: String) : UserInfo
}
package com.example.duty_timer.model.auth

import com.example.duty_timer.model.auth.entities.SignInRequestEntity
import com.example.duty_timer.model.auth.entities.SignInResponseEntity
import com.example.duty_timer.model.auth.entities.SignUpRequestEntity
import com.example.duty_timer.model.auth.entities.SignUpResponseEntity
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Header

interface AuthApi {
    @POST("/auth/sign-in")
    suspend fun signIn(@Body body: SignInRequestEntity): SignInResponseEntity

    @POST("/auth/sign-up")
    suspend fun signUp(@Body body: SignUpRequestEntity): SignUpResponseEntity

    @POST("/auth/log-out")
    suspend fun logOut(@Header("Authorization") token: String)
}
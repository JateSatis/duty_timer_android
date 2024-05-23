package com.example.duty_timer.model.timer

import com.example.duty_timer.model.timer.entities.ConnectToTimerResponseBody
import com.example.duty_timer.model.timer.entities.GetTimerResponseBody
import com.example.duty_timer.model.timer.entities.UpdateTimerRequestBody
import com.example.duty_timer.model.timer.entities.UpdateTimerResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TimerApi {
    @GET("/timer")
    suspend fun getTimer(@Header("Authorization") token: String): GetTimerResponseBody

    @PUT("/timer")
    suspend fun updateTimer(@Header("Authorization") token: String, @Body updateTimerRequestBody: UpdateTimerRequestBody) : UpdateTimerResponseBody

    @POST("connect/{timerId}")
    suspend fun connectToTimer(@Header("Authorization") token: String, @Path("timerId") timerId: Int) : ConnectToTimerResponseBody

}
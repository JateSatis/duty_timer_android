package com.example.duty_timer.model.timer

import com.example.duty_timer.model.sourcesBase.BaseRetrofitSource
import com.example.duty_timer.model.sourcesBase.RetrofitConfig
import com.example.duty_timer.model.timer.entities.ConnectToTimerResponseBody
import com.example.duty_timer.model.timer.entities.GetTimerResponseBody
import com.example.duty_timer.model.timer.entities.UpdateTimerRequestBody
import com.example.duty_timer.model.timer.entities.UpdateTimerResponseBody

class RetrofitTimerSource(config: RetrofitConfig) : TimerSource, BaseRetrofitSource(config) {
    private val timerApi = retrofit.create(TimerApi::class.java)

    override suspend fun getTimer(token: String): GetTimerResponseBody {
        return timerApi.getTimer(token);
    }

    override suspend fun updateTimer(
        token: String,
        startTime: Long,
        endTime: Long
    ): UpdateTimerResponseBody {
        val updateTimerRequestBody = UpdateTimerRequestBody(startTime, endTime);
        return timerApi.updateTimer(token, updateTimerRequestBody);
    }

    override suspend fun connectToTimer(token: String, timerId: Int): ConnectToTimerResponseBody {
        return timerApi.connectToTimer(token, timerId);
    }
}
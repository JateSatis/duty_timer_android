package com.example.duty_timer.model.userInfo

import com.example.duty_timer.model.userInfo.entitites.UserInfo
import com.example.duty_timer.model.sourcesBase.BaseRetrofitSource
import com.example.duty_timer.model.sourcesBase.RetrofitConfig
import kotlinx.coroutines.delay

class RetrofitUserInfoSource(config: RetrofitConfig) : UserInfoSource, BaseRetrofitSource(config) {

    private val userInfoApi = retrofit.create(UserInfoApi::class.java)

    override suspend fun getUserInfo(token: String): UserInfo = wrapRetrofitExceptions {
        return@wrapRetrofitExceptions userInfoApi.getUserInfo(token)
    }
}
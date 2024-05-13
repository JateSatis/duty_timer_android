package com.example.duty_timer.model.auth

import com.example.duty_timer.model.auth.entities.SignInRequestEntity
import com.example.duty_timer.model.auth.entities.SignUpRequestEntity
import com.example.duty_timer.model.sourcesBase.BaseRetrofitSource
import com.example.duty_timer.model.sourcesBase.RetrofitConfig

class RetrofitAuthSource(config: RetrofitConfig) : AuthSource, BaseRetrofitSource(config) {

    private val accountsApi = retrofit.create(AuthApi::class.java)

    override suspend fun singIn(email: String, password: String): String = wrapRetrofitExceptions {
        val signInRequestEntity = SignInRequestEntity(email, password)
        accountsApi.signIn(signInRequestEntity).token
    }

    override suspend fun singUp(
        email: String,
        password: String,
        name: String,
        surname: String,
        nickname: String
    ): String = wrapRetrofitExceptions {
        val signUpRequestEntity = SignUpRequestEntity(
            email,
            password,
            name,
            surname,
            nickname
        )
        accountsApi.signUp(signUpRequestEntity).token
    }

}
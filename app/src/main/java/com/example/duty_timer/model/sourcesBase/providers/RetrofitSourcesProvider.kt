package com.example.duty_timer.model.sourcesBase.providers

import com.example.duty_timer.globals.Consts
import com.example.duty_timer.model.userInfo.RetrofitUserInfoSource
import com.example.duty_timer.model.userInfo.UserInfoSource
import com.example.duty_timer.model.auth.AuthSource
import com.example.duty_timer.model.auth.RetrofitAuthSource
import com.example.duty_timer.model.sourcesBase.RetrofitConfig
import com.example.duty_timer.model.timer.RetrofitTimerSource
import com.example.duty_timer.model.timer.TimerSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitSourcesProvider : SourcesProvider {
    private val config: RetrofitConfig by lazy {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return@lazy RetrofitConfig(
            retrofit = createRetrofit(moshi),
            moshi = moshi
        )
    }

    override val authSource by lazy {
        RetrofitAuthSource(config)
    }
    override val userInfoSource by lazy {
        RetrofitUserInfoSource(config)
    }
    override val timerSource by lazy {
        RetrofitTimerSource(config)
    }

    private fun createRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(if (Consts.production) Consts.BASE_PRODUCTION_URL else Consts.BASE_TEST_URL)
            .client(createOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    /**
     * Create an instance of OkHttpClient with interceptors for authorization
     * and logging (see [createAuthorizationInterceptor] and [createLoggingInterceptor]).
     */
    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createLoggingInterceptor())
            .build()
    }

    /**
     * Log requests and responses to LogCat.
     */
    private fun createLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}
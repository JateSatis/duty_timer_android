package com.example.duty_timer.model

import com.example.duty_timer.globals.Singletons
import com.example.duty_timer.model.auth.AuthRepository
import com.example.duty_timer.model.sourcesBase.providers.RetrofitSourcesProvider
import com.example.duty_timer.model.sourcesBase.providers.SourcesProvider

object RepositoriesProvider {

    private val sourcesProvider: SourcesProvider = RetrofitSourcesProvider
    private val appSettings = Singletons.appSettings

    fun getAuthRepository() : AuthRepository {
        return AuthRepository(sourcesProvider.getAuthSource(), appSettings)
    }

}
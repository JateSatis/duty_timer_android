package com.example.duty_timer.model.sourcesBase.providers

import com.example.duty_timer.model.auth.AuthSource

interface SourcesProvider {

    fun getAuthSource() : AuthSource

}
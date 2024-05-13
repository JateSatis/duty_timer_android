package com.example.duty_timer.utils

import android.app.Application
import com.example.duty_timer.globals.Singletons

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Singletons.init(applicationContext)
    }

}
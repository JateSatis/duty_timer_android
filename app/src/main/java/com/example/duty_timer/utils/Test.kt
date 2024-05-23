package com.example.duty_timer.utils

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

internal class MyThread : Thread() {
    override fun run() {
        println("Running")
    }
}

object ThreadTest {
    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val r: Runnable = MyThread() // #1
        val myThread = Thread(r) // #2
        myThread.start()
    }
}
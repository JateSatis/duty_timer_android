package com.example.duty_timer.utils

interface HasProgressBar {

    fun <T> updateProgressBar(result: Result<T>)

}
package com.example.duty_timer.screens.datePicker

import java.io.Serializable

data class DateForPicker(
    var year: Int,
    var month: Int,
    var day: Int
) : Serializable

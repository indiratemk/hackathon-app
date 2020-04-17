package com.example.hackathon.util.ui

import java.text.SimpleDateFormat
import java.util.*

object DateFormat {

    val DATE_FORMAT_1 = "dd MMM"
    val DATE_FORMAT_2 = "dd MMMM"
    val DATE_FORMAT_3 = "dd.MM.yy"
    val TIME_FORMAT_1 = "HH:mm"

    fun getFormattedDate(time: Long, format: String): String {
        val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
        return simpleDateFormat.format(time)
    }
}
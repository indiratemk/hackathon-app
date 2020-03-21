package com.example.hackathon.util.ui

import java.text.SimpleDateFormat
import java.util.*

object DateFormat {

    val DATE_FORMAT_1 = "dd MMM"

    fun getFormattedDate(time: Long, format: String): String {
        val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
        return simpleDateFormat.format(time)
    }
}
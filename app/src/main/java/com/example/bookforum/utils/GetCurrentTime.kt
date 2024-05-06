package com.example.bookforum.utils

import android.os.Build
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getCurrentTime(): String {
    val current = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDateTime.now()
    } else {
        TODO("VERSION.SDK_INT < O")
    }

    val formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")
    return current.format(formatter)
}
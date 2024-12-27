package com.example.schoolapp.Presentation.Util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

//handle the time format
fun convertDateString(dateFromApi: String): String {
    // Define the input date format
    val inputFormatter =
        DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)

    // Parse the input date string to a LocalDateTime
    val parsedDate = LocalDateTime.parse(dateFromApi, inputFormatter)

    // Define the output date format
    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    // Format the parsed date to the desired format
    return parsedDate.format(outputFormatter)
}
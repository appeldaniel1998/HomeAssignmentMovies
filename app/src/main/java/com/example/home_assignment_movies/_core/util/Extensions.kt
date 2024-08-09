package com.example.home_assignment_movies._core.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toDate(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // The date format
    return LocalDate.parse(this, formatter) // Parse the string to LocalDate
}
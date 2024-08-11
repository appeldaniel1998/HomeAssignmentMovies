package com.example.home_assignment_movies._core.util

import androidx.compose.foundation.lazy.LazyListState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Convert a string to a LocalDate. The string must be in the format "yyyy-MM-dd"
 */
fun String.toDate(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // The date format
    return LocalDate.parse(this, formatter) // Parse the string to LocalDate
}

/**
 * Check if the LazyListState is scrolled to the end (3rd item from the end)
 */
fun LazyListState.isScrolledToTheEnd() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 3

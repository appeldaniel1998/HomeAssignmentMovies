package com.example.home_assignment_movies._core.domain.models

import androidx.compose.runtime.Immutable
import java.time.LocalDate

/**
 * A class representing a movie. It contains the following properties:
 * - id: the unique identifier of the movie.
 * - title: the title of the movie.
 * - overview: a brief description of the movie.
 * - posterUrl: the URL of the movie's poster.
 * - releaseDate: the release date of the movie.
 * - voteAverage: the average rating of the movie.
 * - isSaved: a flag indicating whether the movie is saved as a favorite.
 */
@Immutable
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val releaseDate: LocalDate,
    val voteAverage: Double,
    val isSaved: Boolean = false
) {
    /**
     * Returns the status of the movie based on its release date and vote average.
     * The status can be one of the following:
     * - TOP_RATED: if the vote average is greater than or equal to 4.
     * - UPCOMING: if the release date is in the future.
     * - NOW_PLAYING: if the release date is within the last two months.
     * @return the status of the movie or null if it does not have a status.
     */
    fun getStatus(): List<MovieStatus> {
        val list = mutableListOf<MovieStatus>()
        val currentDate = LocalDate.now()
        val twoMonthsAgo = currentDate.minusMonths(2)

        if (voteAverage >= 4) {
            list.add(MovieStatus.TOP_RATED)
        }
        if (releaseDate.isAfter(currentDate)) {
            list.add(MovieStatus.UPCOMING)
        }
        if (releaseDate.isAfter(twoMonthsAgo) && releaseDate.isBefore(currentDate.plusDays(1))) {
            list.add(MovieStatus.NOW_PLAYING)
        }

        return list
    }
}

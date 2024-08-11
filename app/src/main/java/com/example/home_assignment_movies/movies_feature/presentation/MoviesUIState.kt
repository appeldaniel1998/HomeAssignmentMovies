package com.example.home_assignment_movies.movies_feature.presentation

import com.example.home_assignment_movies._core.domain.models.Movie

/**
 * UI state for the Movies screen.
 * @param isLoading Whether the movies are currently being loaded.
 * @param currentPage The current page of movies.
 * @param currentMovies The movies currently displayed.
 * @see MoviesViewModel
 */
data class MoviesUIState (
    val isLoading: Boolean = false,
    val currentPage: Int = 0,
    val currentMovies: List<Movie> = emptyList()
)
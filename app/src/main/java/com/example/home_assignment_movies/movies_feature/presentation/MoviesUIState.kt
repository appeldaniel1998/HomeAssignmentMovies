package com.example.home_assignment_movies.movies_feature.presentation

import com.example.home_assignment_movies._core.domain.models.Movie

data class MoviesUIState (
    val isLoading: Boolean = false,
    val currentPage: Int = 0,
    val currentMovies: List<Movie> = emptyList()
)
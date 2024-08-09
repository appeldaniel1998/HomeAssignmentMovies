package com.example.home_assignment_movies.movies_feature.presentation

import com.example.home_assignment_movies._core.domain.models.Movie

data class MoviesUIState (
    val currentMovies: List<Movie> = emptyList()
)
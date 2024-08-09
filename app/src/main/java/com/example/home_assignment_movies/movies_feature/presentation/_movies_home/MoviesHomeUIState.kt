package com.example.home_assignment_movies.movies_feature.presentation._movies_home

import com.example.home_assignment_movies._core.domain.models.Movie

data class MoviesHomeUIState (
    val currentMovies: List<Movie> = emptyList()
)
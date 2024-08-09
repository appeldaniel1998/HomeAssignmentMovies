package com.example.home_assignment_movies.movies_feature.presentation._movies_home

import androidx.navigation.NavController
import com.example.home_assignment_movies._core.domain.models.Movie

sealed class MoviesHomeUIEvent {
    data class OnMovieClick(val movieId: Int, val navController: NavController): MoviesHomeUIEvent()
}
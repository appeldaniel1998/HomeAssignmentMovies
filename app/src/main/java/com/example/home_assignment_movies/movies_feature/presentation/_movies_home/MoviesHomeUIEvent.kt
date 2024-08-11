package com.example.home_assignment_movies.movies_feature.presentation._movies_home

import androidx.navigation.NavController
import com.example.home_assignment_movies._core.domain.models.Movie

/**
 * Sealed class for the UI events in the Movies Home screen
 */
sealed class MoviesHomeUIEvent {
    data class OnMovieClick(val movie: Movie, val navController: NavController): MoviesHomeUIEvent()
    class OnFavouriteClick(val movie: Movie) : MoviesHomeUIEvent()

    data object OnLastItemReached: MoviesHomeUIEvent()
}
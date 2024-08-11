package com.example.home_assignment_movies.movies_feature.presentation.movie_details

import com.example.home_assignment_movies._core.domain.models.Movie

/**
 * Represents UI events that can be triggered from the MovieDetailsScreen.
 */
sealed class MovieDetailsUIEvent {
    data class OnFavouriteClick(val movie: Movie) : MovieDetailsUIEvent()
}
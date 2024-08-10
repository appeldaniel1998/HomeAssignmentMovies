package com.example.home_assignment_movies.movies_feature.presentation.movie_details

import com.example.home_assignment_movies._core.domain.models.Movie

sealed class MovieDetailsUIEvent {
    data class OnFavouriteClick(val movie: Movie) : MovieDetailsUIEvent()
}
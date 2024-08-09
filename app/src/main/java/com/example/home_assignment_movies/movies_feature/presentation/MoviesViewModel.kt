package com.example.home_assignment_movies.movies_feature.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.home_assignment_movies._core.presentation.navigation.util.Screens
import com.example.home_assignment_movies.movies_feature.presentation._movies_home.MoviesHomeUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor() : ViewModel() {
    val uiState = mutableStateOf(MoviesUIState())

    fun onEvent(event: MoviesHomeUIEvent) {
        when (event) {
            is MoviesHomeUIEvent.OnMovieClick -> {
                event.navController.navigate(Screens.MovieDetails.route + "/${event.movieId}")
            }
        }
    }
}
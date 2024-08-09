package com.example.home_assignment_movies.movies_feature.presentation._movies_home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.home_assignment_movies._core.presentation.navigation.util.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesHomeViewModel @Inject constructor() : ViewModel() {
    val uiState = mutableStateOf(MoviesHomeUIState())

    fun onEvent(event: MoviesHomeUIEvent) {
        when (event) {
            is MoviesHomeUIEvent.OnMovieClick -> {
                event.navController.navigate(Screens.MovieDetails.route)
                // Handle on movie click event
            }
        }
    }
}
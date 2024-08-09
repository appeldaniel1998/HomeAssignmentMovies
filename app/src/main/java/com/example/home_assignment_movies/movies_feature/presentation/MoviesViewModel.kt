package com.example.home_assignment_movies.movies_feature.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home_assignment_movies._core.presentation.navigation.util.Screens
import com.example.home_assignment_movies.movies_feature.domain.use_cases.GetMoviesUseCase
import com.example.home_assignment_movies.movies_feature.presentation._movies_home.MoviesHomeUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    val uiState = mutableStateOf(MoviesUIState())

    init {
        viewModelScope.launch {
            getMoviesUseCase(1).let {
                uiState.value = uiState.value.copy(currentMovies = it.data ?: emptyList())
            }
        }
    }

    fun onEvent(event: MoviesHomeUIEvent) {
        when (event) {
            is MoviesHomeUIEvent.OnMovieClick -> {
                event.navController.navigate(Screens.MovieDetails.route + "/${event.movieId}")
            }
        }
    }
}
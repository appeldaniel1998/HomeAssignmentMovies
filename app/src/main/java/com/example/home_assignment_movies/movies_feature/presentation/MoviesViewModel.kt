package com.example.home_assignment_movies.movies_feature.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home_assignment_movies._core.presentation.navigation.util.Screens
import com.example.home_assignment_movies._core.presentation.util.UiEvent
import com.example.home_assignment_movies._core.presentation.util.UiText
import com.example.home_assignment_movies._core.util.Resource
import com.example.home_assignment_movies.movies_feature.domain.use_cases.GetMoviesUseCase
import com.example.home_assignment_movies.movies_feature.domain.use_cases.GetTrailerUseCase
import com.example.home_assignment_movies.movies_feature.presentation._movies_home.MoviesHomeUIEvent
import com.example.home_assignment_movies.movies_feature.presentation.movie_details.MovieDetailsUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Movies feature
 * @param getMoviesUseCase Use case for getting movies - received from Hilt
 */
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getTrailerUseCase: GetTrailerUseCase
) : ViewModel() {
    val uiState = mutableStateOf(MoviesUIState()) // Initial state
    private var debounceFlag = false // Flag to prevent multiple loads

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    /**
     * Emit a UI event to be handled by the UI
     */
    fun emitEvent(event: UiEvent) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    init { // Load the first page of movies when the ViewModel is created
        loadNextMovies()
    }

    /**
     * Handle UI events of the MovieDetails screen
     */
    fun onEvent(event: MovieDetailsUIEvent) {
        when (event) {
            is MovieDetailsUIEvent.OnFavouriteClick -> {
                toggleFavourite(event.movie.id)
            }
        }
    }

    /**
     * Handle UI events of the MoviesHome screen
     */
    fun onEvent(event: MoviesHomeUIEvent) {
        when (event) {
            is MoviesHomeUIEvent.OnMovieClick -> {
                event.navController.navigate(Screens.MovieDetails.route + "/${event.movie.id}")

                viewModelScope.launch {
                    when (val trailerKeyResource = getTrailerUseCase(event.movie.id)) {
                        is Resource.Error -> {
                            emitEvent(UiEvent.ShowSnackbar(
                                UiText.DynamicString("Error loading trailer: ${trailerKeyResource.uiText!!.asString(event.navController.context)}"))
                            )
                        }
                        is Resource.Success -> {
                            if (trailerKeyResource.data != null) {
                                event.movie.trailerKey = trailerKeyResource.data
                            }
                        }
                    }


                }
            }

            MoviesHomeUIEvent.OnLastItemReached -> {
                loadNextMovies()
            }

            is MoviesHomeUIEvent.OnFavouriteClick -> {
                toggleFavourite(event.movie.id)
            }
        }
    }

    /**
     * Toggle the favourite status of a movie
     */
    private fun toggleFavourite(movieId: Int) {
        val updatedMoviesList = uiState.value.currentMovies.map { movie ->
            if (movie.id == movieId) {
                movie.copy(isSaved = !movie.isSaved) // Update isSaved
            } else {
                movie
            }
        }

        uiState.value = uiState.value.copy(currentMovies = updatedMoviesList)
    }

    /**
     * Load the next page of movies
     */
    private fun loadNextMovies() {
        // Prevent multiple loads
        if (uiState.value.isLoading || debounceFlag) return // Return if already loading or if debounce flag is set
        debounceFlag = true

        // Set loading state before starting the load
        uiState.value = uiState.value.copy(
            currentPage = uiState.value.currentPage + 1,
            isLoading = true
        )

        viewModelScope.launch {
            try {
                // Perform the loading operation
                when (val moviesResult = getMoviesUseCase(uiState.value.currentPage)) {
                    is Resource.Error -> {
                        emitEvent(UiEvent.ShowSnackbar(moviesResult.uiText!!))
                    }
                    is Resource.Success -> {
                        val newMovies = moviesResult.data!! // Get the new movies from the result
                        val updatedMoviesList = uiState.value.currentMovies + newMovies // Add the new movies to the existing list
                        uiState.value = uiState.value.copy(currentMovies = updatedMoviesList) // Update the state with the combined list
                    }
                }
            } finally {
                // Reset loading state after operation completes
                uiState.value = uiState.value.copy(isLoading = false)
                debounceFlag = false
                Log.d("MoviesViewModel", "Finished loading movies, current page: ${uiState.value.currentPage}")
            }
        }
    }
}
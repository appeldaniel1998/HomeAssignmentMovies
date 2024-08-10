package com.example.home_assignment_movies.movies_feature.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home_assignment_movies._core.presentation.navigation.util.Screens
import com.example.home_assignment_movies.movies_feature.domain.use_cases.GetMoviesUseCase
import com.example.home_assignment_movies.movies_feature.presentation._movies_home.MoviesHomeUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    val uiState = mutableStateOf(MoviesUIState())
    private var debounceFlag = false

    init {
        loadNextMovies()
    }

    fun onEvent(event: MoviesHomeUIEvent) {
        when (event) {
            is MoviesHomeUIEvent.OnMovieClick -> {
                event.navController.navigate(Screens.MovieDetails.route + "/${event.movieId}")
            }

            MoviesHomeUIEvent.OnLastItemReached -> {
                loadNextMovies()
            }
        }
    }

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
                getMoviesUseCase(uiState.value.currentPage).let { result ->
                    val newMovies = result.data ?: emptyList() // Get the new movies from the result
                    val updatedMoviesList = uiState.value.currentMovies + newMovies // Add the new movies to the existing list
                    uiState.value = uiState.value.copy(currentMovies = updatedMoviesList) // Update the state with the combined list
                }
            } finally {
                // Reset loading state after operation completes
                uiState.value = uiState.value.copy(isLoading = false)
                debounceFlag = false
                println("Finished loading movies, current page: ${uiState.value.currentPage}")
            }
        }
    }
}
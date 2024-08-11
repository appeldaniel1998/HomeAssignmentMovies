package com.example.home_assignment_movies.movies_feature.presentation.saved_movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.home_assignment_movies._core.presentation.util.ScaffoldConfig
import com.example.home_assignment_movies._core.util.UiText
import com.example.home_assignment_movies.movies_feature.presentation.MoviesViewModel
import com.example.home_assignment_movies.movies_feature.presentation._movies_home.MoviesHomeUIEvent
import com.example.home_assignment_movies.movies_feature.presentation.components.MoviesListUI
import com.example.homeassignmentmovies.R

/**
 * Saved Movies UI Screen (persistent for the current session)
 */
@Composable
fun SavedMoviesUIScreen(
    navController: NavController,
    scaffoldConfig: MutableState<ScaffoldConfig>,
    viewModel: MoviesViewModel
) {
    LaunchedEffect(Unit) { // Set up of the scaffold
        scaffoldConfig.value = ScaffoldConfig(
            topAppBarTitle = UiText.StringResource(R.string.saved_movies)
        )
    }
    MoviesListUI( // Display the saved movies
        moviesList = viewModel.uiState.value.currentMovies.filter { it.isSaved },
        onItemClick = {
            viewModel.onEvent(MoviesHomeUIEvent.OnMovieClick(it.id, navController))
        },
        onFavouriteClick = {
            viewModel.onEvent(MoviesHomeUIEvent.OnFavouriteClick(it))
        }
    )
}
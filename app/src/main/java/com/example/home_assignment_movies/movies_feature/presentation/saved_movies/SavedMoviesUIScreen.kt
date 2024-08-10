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
import com.example.home_assignment_movies.movies_feature.presentation.MoviesViewModel
import com.example.home_assignment_movies.movies_feature.presentation._movies_home.MoviesHomeUIEvent
import com.example.home_assignment_movies.movies_feature.presentation.components.MoviesListUI

@Composable
fun SavedMoviesUIScreen(
    navController: NavController,
    scaffoldConfig: MutableState<ScaffoldConfig>,
    viewModel: MoviesViewModel
) {
    LaunchedEffect(Unit) {
        scaffoldConfig.value = ScaffoldConfig(
            topAppBarTitle = "Saved Movies"
        )
    }
    Box(modifier = Modifier.fillMaxSize()) {
        MoviesListUI(
            moviesList = viewModel.uiState.value.currentMovies.filter { it.isSaved },
            onItemClick = {
                viewModel.onEvent(MoviesHomeUIEvent.OnMovieClick(it.id, navController))
            },
            onFavouriteClick = {
                viewModel.onEvent(MoviesHomeUIEvent.OnFavouriteClick(it))
            }
        )

        if (viewModel.uiState.value.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Center)
            )
        }
    }
}
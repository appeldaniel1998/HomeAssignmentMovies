package com.example.home_assignment_movies.movies_feature.presentation._movies_home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.home_assignment_movies._core.presentation.util.ScaffoldConfig
import com.example.home_assignment_movies._core.presentation.util.UiEvent
import com.example.home_assignment_movies._core.presentation.util.UiText
import com.example.home_assignment_movies.movies_feature.presentation.MoviesViewModel
import com.example.home_assignment_movies.movies_feature.presentation.components.MoviesListUI
import com.example.homeassignmentmovies.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MoviesHomeUIScreen(
    navController: NavController,
    scaffoldConfig: MutableState<ScaffoldConfig>,
    snackbarHostState: SnackbarHostState,
    viewModel: MoviesViewModel
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) { // Set up of the scaffold
        scaffoldConfig.value = ScaffoldConfig(
            topAppBarTitle = UiText.StringResource(R.string.home),
        )
    }

    LaunchedEffect(Unit) { // Show snackbar
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context),
                        duration = SnackbarDuration.Short,
                        withDismissAction = true
                    )
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) { // Display the movies list
        MoviesListUI( // Display the movies list
            moviesList = viewModel.uiState.value.currentMovies,
            onItemClick = {
                viewModel.onEvent(MoviesHomeUIEvent.OnMovieClick(it, navController))
            },
            onLastItemReached = {
                if (!viewModel.uiState.value.isLoading) {
                    viewModel.onEvent(MoviesHomeUIEvent.OnLastItemReached)
                }
            },
            onFavouriteClick = {
                viewModel.onEvent(MoviesHomeUIEvent.OnFavouriteClick(it))
            }
        )

        if (viewModel.uiState.value.isLoading) { // Display a loading indicator
            CircularProgressIndicator(
                modifier = Modifier.align(Center)
            )
        }
    }
}

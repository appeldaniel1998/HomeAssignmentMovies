package com.example.home_assignment_movies._core.presentation.navigation.nav_graphs

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.home_assignment_movies._core.presentation.navigation.util.Screens
import com.example.home_assignment_movies._core.presentation.util.ScaffoldConfig
import com.example.home_assignment_movies.movies_feature.presentation.MoviesViewModel
import com.example.home_assignment_movies.movies_feature.presentation._movies_home.MoviesHomeUIScreen
import com.example.home_assignment_movies.movies_feature.presentation.movie_details.MovieDetailsUIScreen
import com.example.home_assignment_movies.movies_feature.presentation.saved_movies.SavedMoviesUIScreen

/**
 * Main NavGraph for the app
 * @param navController: NavHostController
 * @param scaffoldConfig: MutableState<ScaffoldConfig> - the scaffold configuration for the app (top bar title, etc.)
 */
@Composable
fun MainNavGraph(
    navController: NavHostController,
    scaffoldConfig: MutableState<ScaffoldConfig>,
    snackbarHostState: SnackbarHostState
) {
    val viewModel: MoviesViewModel = hiltViewModel() // This vieModel is used in the three screens below, and is passed as a parameter to each of them
    // it is a shared viewModel between the three screens because it uses the same data (movie list) to reduce the amount of data transfers and http requests

    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
        exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
        popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
        popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
    ) {
        composable(route = Screens.Home.route) {
            MoviesHomeUIScreen(navController = navController, scaffoldConfig = scaffoldConfig, snackbarHostState = snackbarHostState, viewModel = viewModel)
        }

        composable(route = Screens.SavedMovies.route) {
            SavedMoviesUIScreen(navController = navController, scaffoldConfig = scaffoldConfig, viewModel = viewModel)
        }

        composable(
            route = Screens.MovieDetails.route + "/{movieId}",
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.IntType
                }
            )

        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            MovieDetailsUIScreen(scaffoldConfig = scaffoldConfig, movieId = movieId!!, viewModel = viewModel)
        }
    }
}
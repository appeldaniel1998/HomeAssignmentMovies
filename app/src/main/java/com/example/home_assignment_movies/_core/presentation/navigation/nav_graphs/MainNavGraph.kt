package com.example.home_assignment_movies._core.presentation.navigation.nav_graphs

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.home_assignment_movies._core.presentation.navigation.util.Screens
import com.example.home_assignment_movies._core.presentation.util.ScaffoldConfig
import com.example.home_assignment_movies.movies_feature.presentation._movies_home.MoviesHomeUIScreen
import com.example.home_assignment_movies.movies_feature.presentation.MoviesViewModel
import com.example.home_assignment_movies.movies_feature.presentation.movie_details.MovieDetailsUIScreen
import com.example.home_assignment_movies.movies_feature.presentation.saved_movies.SavedMoviesUIScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    scaffoldConfig: MutableState<ScaffoldConfig>,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
        exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700)) },
        popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) },
        popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(700)) }
    ) {
        composable(route = Screens.Home.route) { backStackEntry ->
            val viewModel: MoviesViewModel = hiltViewModel(backStackEntry)
            MoviesHomeUIScreen(navController = navController, scaffoldConfig = scaffoldConfig, viewModel = viewModel)
        }

        composable(route = Screens.SavedMovies.route) {
            SavedMoviesUIScreen(navController = navController, scaffoldConfig = scaffoldConfig)
        }

        composable(route = Screens.MovieDetails.route) {
            val viewModel: MoviesViewModel = if (navController.previousBackStackEntry != null) hiltViewModel(navController.previousBackStackEntry!!) else hiltViewModel()
            MovieDetailsUIScreen(navController = navController, scaffoldConfig = scaffoldConfig, viewModel = viewModel)
        }
    }
}
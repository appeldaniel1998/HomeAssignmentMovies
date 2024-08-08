package com.example.home_assignment_movies._core.presentation.navigation.nav_graphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.home_assignment_movies._core.presentation.navigation.util.Screens
import com.example.home_assignment_movies._core.presentation.util.ScaffoldConfig
import com.example.home_assignment_movies.movies_feature.presentation._movies_home.MoviesHomeUIScreen
import com.example.home_assignment_movies.movies_feature.presentation.saved_movies.SavedMoviesUIScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    scaffoldConfig: MutableState<ScaffoldConfig>,
) {
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            MoviesHomeUIScreen(navController = navController, scaffoldConfig = scaffoldConfig)
        }

        composable(route = Screens.SavedMovies.route) {
            SavedMoviesUIScreen(navController = navController, scaffoldConfig = scaffoldConfig)
        }
    }
}
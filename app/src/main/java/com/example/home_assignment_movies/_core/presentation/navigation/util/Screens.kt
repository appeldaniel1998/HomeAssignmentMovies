package com.example.home_assignment_movies._core.presentation.navigation.util

sealed class Screens(val route: String) {
    data object Home : Screens("home")
    data object MovieDetails : Screens("movie_details")
    data object SavedMovies : Screens("saved_movies")
}
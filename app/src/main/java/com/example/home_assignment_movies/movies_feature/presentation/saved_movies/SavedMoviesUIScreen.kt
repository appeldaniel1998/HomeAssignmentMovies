package com.example.home_assignment_movies.movies_feature.presentation.saved_movies

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import com.example.home_assignment_movies._core.presentation.util.ScaffoldConfig

@Composable
fun SavedMoviesUIScreen(
    navController: NavController,
    scaffoldConfig: MutableState<ScaffoldConfig>
) {
    Text("Saved Movies UI Screen")
}
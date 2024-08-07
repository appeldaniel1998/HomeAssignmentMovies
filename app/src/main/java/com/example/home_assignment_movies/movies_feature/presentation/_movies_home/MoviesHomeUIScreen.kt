package com.example.home_assignment_movies.movies_feature.presentation._movies_home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import com.example.home_assignment_movies._core.presentation.util.ScaffoldConfig

@Composable
fun MoviesHomeUIScreen(
    navController: NavController,
    scaffoldConfig: MutableState<ScaffoldConfig>
) {
    Text("Movies Home UI Screen")
}
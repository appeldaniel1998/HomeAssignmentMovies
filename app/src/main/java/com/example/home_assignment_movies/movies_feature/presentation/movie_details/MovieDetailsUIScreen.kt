package com.example.home_assignment_movies.movies_feature.presentation.movie_details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.home_assignment_movies._core.presentation.util.ScaffoldConfig

@Composable
fun MovieDetailsUIScreen(
    navController: NavController,
    scaffoldConfig: MutableState<ScaffoldConfig>,
) {
    Text(text = "Movie Details")
}
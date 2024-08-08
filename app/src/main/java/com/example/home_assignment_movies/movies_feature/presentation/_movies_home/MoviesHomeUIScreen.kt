package com.example.home_assignment_movies.movies_feature.presentation._movies_home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.home_assignment_movies._core.presentation.util.ScaffoldConfig

@Composable
fun MoviesHomeUIScreen(
    navController: NavController,
    scaffoldConfig: MutableState<ScaffoldConfig>
) {
    LaunchedEffect(Unit) {
        scaffoldConfig.value = ScaffoldConfig(
            topAppBarTitle = "Home"
        )
    }
    MoviesHomeUI()
    Text("Movies Home UI Screen")
}

@Composable
fun MoviesHomeUI() {
    LazyColumn {
    }
}

@Preview
@Composable
private fun MoviesHomePreview() {
    MoviesHomeUI()
}
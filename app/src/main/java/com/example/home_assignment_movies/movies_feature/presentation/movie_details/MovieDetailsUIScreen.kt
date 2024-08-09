package com.example.home_assignment_movies.movies_feature.presentation.movie_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.home_assignment_movies._core.domain.models.Movie
import com.example.home_assignment_movies._core.presentation.util.ScaffoldConfig
import com.example.home_assignment_movies.movies_feature.presentation.MoviesViewModel

@Composable
fun MovieDetailsUIScreen(
    navController: NavController,
    scaffoldConfig: MutableState<ScaffoldConfig>,
    movieId: Int,
    viewModel: MoviesViewModel
) {
    LaunchedEffect(Unit) {
        scaffoldConfig.value = ScaffoldConfig(
            topAppBarTitle = "Home"
        )
    }

    val currMovie = viewModel.uiState.value.currentMovies.find { it.id == movieId }
    if (currMovie != null) {
        MovieDetailsUI(currMovie)
    }
}

@Composable
fun MovieDetailsUI(currMovie: Movie) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = currMovie.posterUri),
            contentDescription = "${currMovie.title} Poster",
            modifier = Modifier.height(250.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${currMovie.title} (${currMovie.releaseDate.year})",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Rating: ${currMovie.voteAverage}",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Overview",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
            )
        Text(
            text = currMovie.overview,
            modifier = Modifier.align(Alignment.Start)
        )
    }

}
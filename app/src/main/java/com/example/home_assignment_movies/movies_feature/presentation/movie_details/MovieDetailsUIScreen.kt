package com.example.home_assignment_movies.movies_feature.presentation.movie_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import coil.compose.AsyncImage
import com.example.home_assignment_movies._core.domain.models.Movie
import com.example.home_assignment_movies._core.presentation.util.ScaffoldConfig
import com.example.home_assignment_movies.movies_feature.presentation.MoviesViewModel
import com.example.home_assignment_movies.movies_feature.presentation.components.FavouriteIcon

/**
 * Represents the UI of the Movie Details screen.
 */
@Composable
fun MovieDetailsUIScreen(
    scaffoldConfig: MutableState<ScaffoldConfig>,
    movieId: Int,
    viewModel: MoviesViewModel
) {
    LaunchedEffect(Unit) { // Set up of the scaffold configuration
        scaffoldConfig.value = ScaffoldConfig(
            topAppBarTitle = "Movie Details"
        )
    }

    val currMovie = viewModel.uiState.value.currentMovies.find { it.id == movieId }

    if (currMovie != null) { // If the movie is found, display the details
        MovieDetailsUI(currMovie) {
            viewModel.onEvent(MovieDetailsUIEvent.OnFavouriteClick(it))
        }
    }
}

/**
 * Represents the UI of the Movie Details screen.
 */
@Composable
fun MovieDetailsUI(
    currMovie: Movie,
    onFavouriteClick: (Movie) -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) { // Display the movie details
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage( // Display the movie poster
                model = currMovie.posterUrl,
                contentDescription = "${currMovie.title} Poster",
                modifier = Modifier.height(250.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text( // Display the movie title and release year
                text = "${currMovie.title} (${currMovie.releaseDate.year})",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Text( // Display the movie rating
                text = "Rating: ${currMovie.voteAverage}",
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text( // Display the movie overview title
                text = "Overview",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Text( // Display the movie overview
                text = currMovie.overview,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        FavouriteIcon( // Display the favourite icon
            currMovie = currMovie,
            onFavouriteClick = onFavouriteClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(50.dp)
        )
    }
}
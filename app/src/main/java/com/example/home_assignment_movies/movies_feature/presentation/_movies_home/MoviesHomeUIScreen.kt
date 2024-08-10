package com.example.home_assignment_movies.movies_feature.presentation._movies_home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.home_assignment_movies._core.domain.models.Movie
import com.example.home_assignment_movies._core.domain.models.MovieStatus
import com.example.home_assignment_movies._core.presentation.util.ScaffoldConfig
import com.example.home_assignment_movies._core.util.isScrolledToTheEnd
import com.example.home_assignment_movies.movies_feature.presentation.MoviesViewModel
import com.example.homeassignmentmovies.R

@Composable
fun MoviesHomeUIScreen(
    navController: NavController,
    scaffoldConfig: MutableState<ScaffoldConfig>,
    viewModel: MoviesViewModel
) {
    LaunchedEffect(Unit) {
        scaffoldConfig.value = ScaffoldConfig(
            topAppBarTitle = "Home"
        )
    }
    Box(modifier = Modifier.fillMaxSize()) {
        MoviesHomeUI(
            moviesList = viewModel.uiState.value.currentMovies,
            onItemClick = {
                viewModel.onEvent(MoviesHomeUIEvent.OnMovieClick(it.id, navController))
            },
            onLastItemReached = {
                if (!viewModel.uiState.value.isLoading) {
                    viewModel.onEvent(MoviesHomeUIEvent.OnLastItemReached)
                }
            }
        )

        if (viewModel.uiState.value.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Center)
            )
        }
    }
}

@Composable
fun MoviesHomeUI(
    moviesList: List<Movie> = emptyList(),
    onItemClick: (Movie) -> Unit = {},
    onLastItemReached: () -> Unit = {}
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.drawable.tmdblogo)
                        .decoderFactory(SvgDecoder.Factory())
                        .build()
                ),
                contentDescription = "TMDB Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp)
            )
        }
        items(moviesList.size) { index ->
            if (lazyListState.isScrolledToTheEnd()) { // Check if the list is scrolled to the end, then call onLastItemReached (which loads more movies)
                onLastItemReached()
            }

            val currMovie = moviesList[index]
            Card(
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .clickable { onItemClick(currMovie) }
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    AsyncImage(
                        model = currMovie.posterUrl,
                        contentDescription = "${currMovie.title} poster",
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp)),
                        alignment = Alignment.Center
                    )

                    val movieStatusList = currMovie.getStatus()
                    Row(
                        modifier = Modifier.align(Alignment.TopStart),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (movieStatusList.contains(MovieStatus.TOP_RATED)) {
                            MovieStatusComp("Top Rated")
                        }
                        if (movieStatusList.contains(MovieStatus.NOW_PLAYING)) {
                            MovieStatusComp("Now Playing")
                        }
                        if (movieStatusList.contains(MovieStatus.UPCOMING)) {
                            MovieStatusComp("Upcoming")
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun MovieStatusComp(text: String) {
    Box(
        modifier = Modifier
            .background(Color.Black.copy(alpha = 0.7f), shape = RoundedCornerShape(8.dp))
            .padding(4.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

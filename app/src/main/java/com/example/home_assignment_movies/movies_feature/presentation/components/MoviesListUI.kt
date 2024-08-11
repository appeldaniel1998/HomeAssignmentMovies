package com.example.home_assignment_movies.movies_feature.presentation.components

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.home_assignment_movies._core.domain.models.Movie
import com.example.home_assignment_movies._core.domain.models.MovieStatus
import com.example.home_assignment_movies._core.util.isScrolledToTheEnd
import com.example.homeassignmentmovies.R

/**
 * UI for displaying a list of movies
 * @param moviesList List of movies to display
 * @param onItemClick Function to call when a movie is clicked
 * @param onLastItemReached Function to call when the end of the list is (almost) reached
 * @param onFavouriteClick Function to call when the favourite icon is clicked
 */
@Composable
fun MoviesListUI(
    moviesList: List<Movie> = emptyList(),
    onItemClick: (Movie) -> Unit = {},
    onLastItemReached: () -> Unit = {},
    onFavouriteClick: (Movie) -> Unit = {}
) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Image( // TMDB logo
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
        items( // Display the movies
            items = moviesList,
            key = { movie -> movie.id }
        ) { currMovie ->
            val isAtEnd = remember { derivedStateOf { lazyListState.isScrolledToTheEnd() } }
            if (isAtEnd.value) { // Check if the list is scrolled (almost) to the end, then call onLastItemReached (which loads more movies)
                onLastItemReached()
            }

            Card( // Movie card
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .clickable { onItemClick(currMovie) }
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) { // Box for the movie poster
                    AsyncImage(
                        model = currMovie.posterUrl,
                        contentDescription = stringResource(R.string.poster, currMovie.title),
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp)),
                        alignment = Center
                    )

                    val movieStatusList = remember(currMovie) { derivedStateOf { currMovie.getStatus() } } // Get the movie status list

                    Row( // Row for the movie status
                        modifier = Modifier.align(Alignment.TopStart),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (movieStatusList.value.contains(MovieStatus.TOP_RATED)) {
                            MovieStatusComp(stringResource(R.string.top_rated))
                        }
                        if (movieStatusList.value.contains(MovieStatus.NOW_PLAYING)) {
                            MovieStatusComp(stringResource(R.string.now_playing))
                        }
                        if (movieStatusList.value.contains(MovieStatus.UPCOMING)) {
                            MovieStatusComp(stringResource(R.string.upcoming))
                        }
                    }

                    FavouriteIcon( // Favourite icon
                        currMovie = currMovie,
                        onFavouriteClick = onFavouriteClick,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(40.dp)
                    )
                }
            }
        }
    }
}

/**
 * UI for displaying the movie status
 * @param text Text to display
 */
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
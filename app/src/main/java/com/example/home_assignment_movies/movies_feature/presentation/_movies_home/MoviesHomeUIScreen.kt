package com.example.home_assignment_movies.movies_feature.presentation._movies_home

import android.net.Uri
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.home_assignment_movies._core.domain.models.Movie
import com.example.home_assignment_movies._core.domain.models.MovieStatus
import com.example.home_assignment_movies._core.presentation.util.ScaffoldConfig
import com.example.home_assignment_movies.movies_feature.data.remote.RemoteConstants
import com.example.home_assignment_movies.movies_feature.presentation.MoviesViewModel
import com.example.homeassignmentmovies.R
import java.time.LocalDate

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
    MoviesHomeUI() {
        viewModel.onEvent(MoviesHomeUIEvent.OnMovieClick(it, navController))
    }
}

@Composable
fun MoviesHomeUI(
    moviesList: List<Movie> = emptyList(),
    onItemClick: (Movie) -> Unit = {}
) {
    val moviesList = listOf(
        Movie(
            id = 1,
            title = "3 Days in Malay",
            overview = "Marines stationed at an airfield in Malay during WWII get wind of a coming raid by the Japanese. Unable to get reinforcements approved, they engage in a harrowing 3-day battle against enemy forces.",
            posterUri = Uri.parse("${RemoteConstants.IMAGE_BASE_URL}/hqnfqeCILvgKGWKOut5lVdxdeJh.jpg"),
            releaseDate = LocalDate.now(),
            voteAverage = 4.9f
        ),
        Movie(
            id = 2,
            title = "Deadpool",
            overview = "The origin story of former Special Forces operative turned mercenary Wade Wilson, who, after being subjected to a rogue experiment that leaves him with accelerated healing powers, adopts the alter ego Deadpool. Armed with his new abilities and a dark, twisted sense of humor, Deadpool hunts down the man who nearly destroyed his life.",
            posterUri = Uri.parse("${RemoteConstants.IMAGE_BASE_URL}/3E53WEZJqP6aM84D8CckXx4pIHw.jpg"),
            releaseDate = LocalDate.now().minusYears(1),
            voteAverage = 2.0f
        ),
        Movie(
            id = 3,
            title = "My Spy The Eternal City",
            overview = "JJ, a veteran CIA agent, reunites with his protégé Sophie, in order to prevent a catastrophic nuclear plot targeting the Vatican.",
            posterUri = Uri.parse("${RemoteConstants.IMAGE_BASE_URL}/Bf3vCfM94bSJ1saZlyi0UW0e0U.jpg"),
            releaseDate = LocalDate.now(),
            voteAverage = 3.0f
        ),
    )

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(R.drawable.tmdblogo)
            .decoderFactory(SvgDecoder.Factory())
            .build()
    )

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Image(
                painter = painter,
                contentDescription = "TMDB Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp)
            )
        }
        items(moviesList.size) { index ->
            val currMovie = moviesList[index]
            Card(
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .clickable { onItemClick(currMovie) }
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = rememberAsyncImagePainter(currMovie.posterUri),
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

@Preview(showBackground = true)
@Composable
private fun MoviesHomePreview() {
    val baseUrl = RemoteConstants.IMAGE_BASE_URL
    MoviesHomeUI(
        moviesList = listOf(
            Movie(
                id = 1,
                title = "3 Days in Malay",
                overview = "Marines stationed at an airfield in Malay during WWII get wind of a coming raid by the Japanese. Unable to get reinforcements approved, they engage in a harrowing 3-day battle against enemy forces.",
                posterUri = Uri.parse("$baseUrl/hqnfqeCILvgKGWKOut5lVdxdeJh.jpg"),
                releaseDate = LocalDate.now(),
                voteAverage = 4.9f
            ),
            Movie(
                id = 2,
                title = "Deadpool",
                overview = "The origin story of former Special Forces operative turned mercenary Wade Wilson, who, after being subjected to a rogue experiment that leaves him with accelerated healing powers, adopts the alter ego Deadpool. Armed with his new abilities and a dark, twisted sense of humor, Deadpool hunts down the man who nearly destroyed his life.",
                posterUri = Uri.parse("$baseUrl/3E53WEZJqP6aM84D8CckXx4pIHw.jpg"),
                releaseDate = LocalDate.now().minusYears(1),
                voteAverage = 2.0f
            ),
            Movie(
                id = 3,
                title = "My Spy The Eternal City",
                overview = "JJ, a veteran CIA agent, reunites with his protégé Sophie, in order to prevent a catastrophic nuclear plot targeting the Vatican.",
                posterUri = Uri.parse("$baseUrl/Bf3vCfM94bSJ1saZlyi0UW0e0U.jpg"),
                releaseDate = LocalDate.now(),
                voteAverage = 3.0f
            ),
        )
    )
}
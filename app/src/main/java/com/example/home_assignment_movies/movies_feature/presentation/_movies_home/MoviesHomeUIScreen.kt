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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import com.example.home_assignment_movies.movies_feature.presentation.components.MoviesListUI
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
        MoviesListUI(
            moviesList = viewModel.uiState.value.currentMovies,
            onItemClick = {
                viewModel.onEvent(MoviesHomeUIEvent.OnMovieClick(it.id, navController))
            },
            onLastItemReached = {
                if (!viewModel.uiState.value.isLoading) {
                    viewModel.onEvent(MoviesHomeUIEvent.OnLastItemReached)
                }
            },
            onFavouriteClick = {
                viewModel.onEvent(MoviesHomeUIEvent.OnFavouriteClick(it))
            }
        )

        if (viewModel.uiState.value.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Center)
            )
        }
    }
}

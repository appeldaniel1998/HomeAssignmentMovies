package com.example.home_assignment_movies.movies_feature.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.home_assignment_movies._core.domain.models.Movie
import com.example.homeassignmentmovies.R

/**
 * Favourite icon that changes based on the current movie's saved state
 */
@Composable
fun FavouriteIcon(
    currMovie: Movie,
    modifier: Modifier = Modifier,
    onFavouriteClick: (Movie) -> Unit
) {
    Icon(
        imageVector = if (currMovie.isSaved) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
        contentDescription = stringResource(R.string.favorite),
        tint = Color.Red,
        modifier = modifier

            .padding(8.dp)
            .clip(CircleShape)
            .clickable { onFavouriteClick(currMovie) }
    )
}
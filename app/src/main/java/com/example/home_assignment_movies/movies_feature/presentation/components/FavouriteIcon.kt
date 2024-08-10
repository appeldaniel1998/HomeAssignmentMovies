package com.example.home_assignment_movies.movies_feature.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.home_assignment_movies._core.domain.models.Movie

@Composable
fun FavouriteIcon(
    modifier: Modifier = Modifier,
    currMovie: Movie,
    onFavouriteClick: (Movie) -> Unit
) {
    Icon(
        imageVector = if (currMovie.isSaved) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
        contentDescription = "Favorite",
        tint = Color.Red,
        modifier = modifier

            .padding(8.dp)
            .clip(CircleShape)
            .clickable { onFavouriteClick(currMovie) }
    )
}
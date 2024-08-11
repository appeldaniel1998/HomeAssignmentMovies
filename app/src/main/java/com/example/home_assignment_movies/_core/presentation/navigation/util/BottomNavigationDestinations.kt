package com.example.home_assignment_movies._core.presentation.navigation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import com.example.home_assignment_movies._core.presentation.util.UiText
import com.example.homeassignmentmovies.R

/**
 * An object that contains the destinations for the bottom navigation bar.
 */
object BottomNavigationDestinations {
    val list = listOf(
        NavigationItem(icon = Icons.Default.Home, title = UiText.StringResource(R.string.home), destinationRoute = Screens.Home.route),
        NavigationItem(icon = Icons.Default.Favorite, title = UiText.StringResource(R.string.saved_movies), destinationRoute = Screens.SavedMovies.route)
    )
}
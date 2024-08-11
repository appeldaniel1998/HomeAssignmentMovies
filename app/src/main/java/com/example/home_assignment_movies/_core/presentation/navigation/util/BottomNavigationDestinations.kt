package com.example.home_assignment_movies._core.presentation.navigation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home

/**
 * An object that contains the destinations for the bottom navigation bar.
 */
object BottomNavigationDestinations {
    val list = listOf(
        NavigationItem(icon = Icons.Default.Home, title = "Home", destinationRoute = Screens.Home.route),
        NavigationItem(icon = Icons.Default.Favorite, title = "Saved Movies", destinationRoute = Screens.SavedMovies.route)
    )
}
package com.example.home_assignment_movies._core.presentation.navigation.util

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * A data class that represents a navigation item in the bottom navigation bar, containing an icon, a title, and a destination route (used for navigation).
 */
data class NavigationItem(
    val icon: ImageVector,
    val title: String,
    val destinationRoute: String
)

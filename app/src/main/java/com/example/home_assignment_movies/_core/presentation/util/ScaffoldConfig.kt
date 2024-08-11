package com.example.home_assignment_movies._core.presentation.util

/**
 * Represents the configuration of a Scaffold.
 * @param topAppBarTitle The title of the top app bar.
 * @param showBackButton Whether to show the back button in the top app bar.
 */
data class ScaffoldConfig(
    val topAppBarTitle: UiText = UiText.Empty,
    val showBackButton: Boolean = false
)

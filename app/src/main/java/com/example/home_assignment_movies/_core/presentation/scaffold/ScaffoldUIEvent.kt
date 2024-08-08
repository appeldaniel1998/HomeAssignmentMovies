package com.example.home_assignment_movies._core.presentation.scaffold

import androidx.navigation.NavController
import com.example.home_assignment_movies._core.presentation.navigation.util.NavigationItem

sealed class ScaffoldUIEvent {
    data class OnNavBarItemSelected(val navController: NavController, val index: Int, val item: NavigationItem): ScaffoldUIEvent()
    data class OnBackPress(val navController: NavController): ScaffoldUIEvent()
}
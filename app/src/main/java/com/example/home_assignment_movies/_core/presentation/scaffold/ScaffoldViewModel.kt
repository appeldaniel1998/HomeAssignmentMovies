package com.example.home_assignment_movies._core.presentation.scaffold

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for the Scaffold composable
 */
@HiltViewModel
class ScaffoldViewModel @Inject constructor(): ViewModel() {
    val uiState = mutableStateOf(ScaffoldUIState())

    /**
     * Handle UI events
     */
    fun onEvent(event: ScaffoldUIEvent) {
        when(event) {
            is ScaffoldUIEvent.OnNavBarItemSelected -> {
                uiState.value = uiState.value.copy(navBarItemSelectedIndex = event.index)
                event.navController.navigate(event.item.destinationRoute)
            }
            is ScaffoldUIEvent.OnBackPress -> {
                event.navController.popBackStack()
            }
        }
    }
}
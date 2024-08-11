package com.example.home_assignment_movies._core.presentation.util

/**
 * Represents a UI event that should be handled by the UI layer.
 * Currently only used to show a snackbar, but can be extended to add more events in the future (e.g. navigation, dialogs etc.).
 */
sealed class UiEvent {
    data class ShowSnackbar(val uiText: UiText) : UiEvent()
}
package com.example.home_assignment_movies._core.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.homeassignmentmovies.R

/**
 * Represents a string that can be either a dynamic string or a string resource.
 * This is useful for UI text that can be either static or dynamic.
 */
sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    data class StringResource(@StringRes val id: Int) : UiText()

    companion object {
        /**
         * A default error message, used when an unknown error occurs.
         */
        val unknownError = StringResource(R.string.unknown_error)

        val Empty = DynamicString("")
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> this.value
            is StringResource -> context.getString(this.id)
        }
    }

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> this.value
            is StringResource -> stringResource(id = this.id)
        }
    }
}
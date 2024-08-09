package com.example.home_assignment_movies._core.util

typealias SimpleResource = Resource<Unit> // A simple resource that doesn't hold any data

/**
 * A generic class that holds a value, or an error message
 * @param <T> The type of the data
 * @property data The data that is being held
 * @property uiText The error message that is being held
 */
sealed class Resource<T>(val data: T? = null, val uiText: UiText? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(uiText: UiText, data: T? = null) : Resource<T>(data, uiText)
}
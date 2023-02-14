package com.anonymous.searchquery.presentaiton.util

sealed class UIState<T> {
    class Nothing<T> : UIState<T>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error<T>(val message: String) : UIState<T>()
    data class Loading<T>(val data: Boolean = true) : UIState<T>()
}

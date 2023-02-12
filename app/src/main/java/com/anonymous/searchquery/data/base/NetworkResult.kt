package com.anonymous.searchquery.data.base

sealed class NetworkResult<T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    class Error<T>(errorCode: String) : NetworkResult<T>()
}

package com.anonymous.searchquery.presentaiton.util

import android.content.Context
import com.anonymous.searchquery.R

interface ResourceManager {
    fun getErrorMessage(type: ErrorType): String
}

enum class ErrorType {
    SEARCH_RESULT_EMPTY,
    API_CALL_ERROR
}

class ResourceManagerImpl(private val context: Context) : ResourceManager {
    override fun getErrorMessage(type: ErrorType): String {
        return when (type) {
            ErrorType.SEARCH_RESULT_EMPTY -> context.getString(R.string.no_result_found)
            ErrorType.API_CALL_ERROR -> context.getString(R.string.try_again)
            else -> context.getString(R.string.no_result_found)
        }
    }
}

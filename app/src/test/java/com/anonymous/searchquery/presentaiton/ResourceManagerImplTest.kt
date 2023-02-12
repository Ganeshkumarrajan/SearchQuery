package com.anonymous.searchquery.presentaiton

import android.content.Context
import com.anonymous.searchquery.R
import com.anonymous.searchquery.presentaiton.util.ErrorType
import com.anonymous.searchquery.presentaiton.util.ResourceManagerImpl
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName

class ResourceManagerImplTest {
    private lateinit var manger: ResourceManagerImpl
    private val context: Context = mockk()

    @Before
    fun setup() {
        manger = ResourceManagerImpl(context)
    }

    @DisplayName("get error message on no search result found")
    @Test
    fun findErrorMessageOnNoResult() {
        every {
            context.getString(R.string.no_result_found)
        } returns "No Result found"
        val errorMessage = manger.getErrorMessage(ErrorType.SEARCH_RESULT_EMPTY)
        Assertions.assertTrue(errorMessage == "No Result found")
    }

    @DisplayName("get error message on APi call failure")
    @Test
    fun findErrorMessageOnApiCAllFailed() {
        every {
            context.getString(R.string.try_again)
        } returns "api error"
        val errorMessage = manger.getErrorMessage(ErrorType.API_CALL_ERROR)
        Assertions.assertTrue(errorMessage == "api error")
    }
}

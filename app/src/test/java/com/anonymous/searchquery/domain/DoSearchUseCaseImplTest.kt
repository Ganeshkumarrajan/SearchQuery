package com.anonymous.searchquery.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anonymous.searchquery.data.base.NetworkResult
import com.anonymous.searchquery.domain.search.repository.MetMuseumRepository
import com.anonymous.searchquery.domain.search.usecase.DoSearchUseCaseImpl
import com.anonymous.searchquery.utils.MainCoroutineRule
import com.anonymous.searchquery.utils.getFakeEmptySearchResult
import com.anonymous.searchquery.utils.getFakeErrorSearchResult
import com.anonymous.searchquery.utils.getFakeSearchResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName

@Suppress("OPT_IN_IS_NOT_ENABLED")
class DoSearchUseCaseImplTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var useCase: DoSearchUseCaseImpl
    private val repository = mockk<MetMuseumRepository>()

    @Before
    fun setUp() {
        this.useCase = DoSearchUseCaseImpl(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @DisplayName("get result for valid keyword")
    @Test
    fun doSearchSuccess() =
        runTest {
            coEvery {
                repository.doSearch("sunFlower")
            } returns getFakeSearchResult()

            val result = useCase.doSearch("sunFlower")
            val flowResult = result.first()
            assert(flowResult is NetworkResult.Success)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @DisplayName("get empty search result")
    @Test
    fun doSearchOnEmptyResult() =
        runTest {
            val inValidKeyWord = "keyWord"

            coEvery {
                repository.doSearch(inValidKeyWord)
            } returns getFakeEmptySearchResult()

            val result = useCase.doSearch(inValidKeyWord)
            val flowResult = result.first()

            assert(flowResult is NetworkResult.Success)
            if (flowResult is NetworkResult.Success) {
                assert(flowResult.data.isEmpty())
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @DisplayName("get empty search result")
    @Test
    fun doSearchResultError() {
        runTest {
            val keyWord = "error"

            coEvery {
                repository.doSearch(query = keyWord)
            } returns getFakeErrorSearchResult()

            val result = useCase.doSearch(keyWord).first()

            assert(result is NetworkResult.Error)
        }
    }
}

package com.anonymous.searchquery.presentaiton

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anonymous.searchquery.domain.search.usecase.DoSearchUseCase
import com.anonymous.searchquery.domain.search.usecase.SearchDomain
import com.anonymous.searchquery.presentaiton.search.model.SearchUI
import com.anonymous.searchquery.presentaiton.search.viewmodel.DomainToUIMapper
import com.anonymous.searchquery.presentaiton.search.viewmodel.SearchViewModel
import com.anonymous.searchquery.presentaiton.search.viewmodel.UserEvent
import com.anonymous.searchquery.presentaiton.util.ErrorType
import com.anonymous.searchquery.presentaiton.util.ResourceManager
import com.anonymous.searchquery.presentaiton.util.UIState
import com.anonymous.searchquery.utils.*
import com.anonymous.searchquery.utils.getFakeEmptySearchResult
import com.anonymous.searchquery.utils.getFakeSearchDomain
import com.anonymous.searchquery.utils.getFakeSearchUI
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName

@Suppress("OPT_IN_IS_NOT_ENABLED")
class SearchViewModelTest {
    private val resourceManger: ResourceManager = mockk()
    private val mapper: DomainToUIMapper<List<SearchDomain?>, List<SearchUI>> = mockk()
    private val useCase: DoSearchUseCase = mockk()
    private lateinit var viewModel: SearchViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = SearchViewModel(useCase, mapper, resourceManger)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun searchWithSuccessCase() {
        runTest {
            val keyWord = "ValidKayWord"
            coEvery {
                useCase.doSearch(keyWord)
            } returns getFakeSearchResult()

            coEvery {
                mapper.map(getFakeSearchDomain())
            } returns getFakeSearchUI()

            viewModel.onEvent(UserEvent.Search(keyWord))

            Assertions.assertTrue(viewModel.searchResult.value is UIState.Success)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @DisplayName("on empty result for invalid keyword")
    @Test
    fun searchWithEmptyResultCase() {
        runTest {
            val keyWord = "inValidKayWord"
            coEvery {
                useCase.doSearch(keyWord)
            } returns getFakeEmptySearchResult()

            every {
                resourceManger.getErrorMessage(ErrorType.SEARCH_RESULT_EMPTY)
            } returns "no result found"
            viewModel.onEvent(UserEvent.Search(keyWord))

            Assertions.assertTrue(viewModel.searchResult.value is UIState.Error)
            if (viewModel.searchResult.value is UIState.Error)
                Assertions.assertTrue((viewModel.searchResult.value as UIState.Error<List<SearchUI>>).message == "no result found")
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @DisplayName("on error while searching")
    @Test
    fun searchWithErrorCase() {
        runTest {
            val keyWord = "inValidKayWord"
            coEvery {
                useCase.doSearch(keyWord)
            } returns getFakeErrorSearchResult()

            every {
                resourceManger.getErrorMessage(ErrorType.API_CALL_ERROR)
            } returns "api error"

            viewModel.onEvent(UserEvent.Search(keyWord))

            Assertions.assertTrue(viewModel.searchResult.value is UIState.Error)
            if (viewModel.searchResult.value is UIState.Error)
                Assertions.assertTrue((viewModel.searchResult.value as UIState.Error<List<SearchUI>>).message == "api error")
        }
    }
}

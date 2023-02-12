package com.anonymous.searchquery.presentaiton.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.searchquery.data.base.NetworkResult
import com.anonymous.searchquery.domain.search.usecase.DoSearchUseCase
import com.anonymous.searchquery.domain.search.usecase.SearchDomain
import com.anonymous.searchquery.presentaiton.search.model.SearchUI
import com.anonymous.searchquery.presentaiton.util.ErrorType
import com.anonymous.searchquery.presentaiton.util.ResourceManager
import com.anonymous.searchquery.presentaiton.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: DoSearchUseCase,
    private val mapper: DomainToUIMapper<List<SearchDomain?>, List<SearchUI>>,
    private val resourceManger: ResourceManager
) : ViewModel() {

    private val _searchResult = MutableStateFlow<UIState<List<SearchUI>>>(UIState.Nothing())
    val searchResult :StateFlow<UIState<List<SearchUI>>> = _searchResult

    fun onEvent(events: UserEvent) {
        when (events) {
            is UserEvent.Search -> doSearch(events.keyword)
        }
    }

    private fun doSearch(keyword: String) {
        _searchResult.value = UIState.Loading()
        viewModelScope.launch {
            useCase.doSearch(keyword).collectLatest {
                parseSearchResult(it)
            }
        }
    }

    private fun parseSearchResult(result: NetworkResult<List<SearchDomain?>>) {
        _searchResult.value = when (result) {
            is NetworkResult.Success -> {
                if (result.data.isEmpty()) UIState.Error(
                    resourceManger.getErrorMessage(
                        ErrorType.SEARCH_RESULT_EMPTY
                    )
                )
                else UIState.Success(mapper.map(result.data))
            }
            is NetworkResult.Error -> {
                UIState.Error(
                    resourceManger.getErrorMessage(
                        ErrorType.API_CALL_ERROR
                    )
                )
            }
        }
    }
}

interface DomainToUIMapper<I, O> {
    fun map(input: I): O
}

class SearchResultMapperDomainToUI() : DomainToUIMapper<List<SearchDomain?>, List<SearchUI>> {
    override fun map(input: List<SearchDomain?>): List<SearchUI> =
        input.map {
            SearchUI(it?.objectId ?: 0)
        }
}

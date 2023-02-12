package com.anonymous.searchquery.utils

import com.anonymous.searchquery.data.base.NetworkResult
import com.anonymous.searchquery.data.search.model.SearchNetwork
import com.anonymous.searchquery.domain.search.usecase.SearchDomain
import com.anonymous.searchquery.presentaiton.search.model.SearchUI
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

internal fun getFakeSearchResult(): kotlinx.coroutines.flow.Flow<NetworkResult<List<SearchDomain?>>> =
    flow {
        emit(
            NetworkResult.Success(
                getFakeSearchDomain()
            )
        )
    }

internal fun getFakeEmptySearchResult(): kotlinx.coroutines.flow.Flow<NetworkResult<List<SearchDomain?>>> =
    flow {
        emit(
            NetworkResult.Success(
                listOf()
            )
        )
    }

internal fun getFakeErrorSearchResult(): Flow<NetworkResult<List<SearchDomain?>>> = flow {
    emit(NetworkResult.Error("10001"))
}

internal fun getFakeSearchDomain(): List<SearchDomain> =
    listOf(
        SearchDomain(1),
        SearchDomain(2),
        SearchDomain(3)
    )

internal fun getFakeEmptySearchDomain(): List<SearchDomain> =
    emptyList()

internal fun getFakeSearchUI(): List<SearchUI> =
    listOf(
        SearchUI(1),
        SearchUI(2),
        SearchUI(3)
    )

internal fun getFakeSearchNetwork(): SearchNetwork =
    SearchNetwork(listOf(1, 2, 3), 4)

internal fun getFakeEmptySearchNetwork(): SearchNetwork =
    SearchNetwork(null, 0)

internal fun fakeSearchApiResponse(): ApiResponse<SearchNetwork> = ApiResponse.Success(
    Response.success(
        getFakeSearchNetwork()
    )
)

internal fun fakeEmptySearchApiResponse(): ApiResponse<SearchNetwork> = ApiResponse.Success(
    Response.success(
        getFakeEmptySearchNetwork()
    )
)

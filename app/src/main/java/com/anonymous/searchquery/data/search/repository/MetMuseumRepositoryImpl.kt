package com.anonymous.searchquery.data.search.repository

import com.anonymous.searchquery.data.api.MetMuseumService
import com.anonymous.searchquery.data.base.NetworkResult
import com.anonymous.searchquery.data.search.mapper.NetworkToDomainMapper
import com.anonymous.searchquery.data.search.model.SearchNetwork
import com.anonymous.searchquery.domain.search.repository.MetMuseumRepository
import com.anonymous.searchquery.domain.search.usecase.SearchDomain
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MetMuseumRepositoryImpl(
    private val service: MetMuseumService,
    private val mapper: NetworkToDomainMapper<SearchNetwork, List<SearchDomain?>>,
    private val dispatcher: CoroutineDispatcher
) : MetMuseumRepository {

    override suspend fun doSearch(query: String): Flow<NetworkResult<List<SearchDomain?>>> = flow {
        emit(convertToNetworkResult(service.searchQuery(query), mapper))
    }.flowOn(dispatcher)
}

private fun <I, O> convertToNetworkResult(
    data: ApiResponse<I>,
    mapper: NetworkToDomainMapper<I, O>
): NetworkResult<O> {
    var result: NetworkResult<O>? = null

    data.onSuccess {
        result = NetworkResult.Success(mapper.mapTo(this.data))
    }.onError {
        result = NetworkResult.Error("")
    }.onException {
        result = NetworkResult.Error("")
    }

    return result ?: NetworkResult.Error("")
}

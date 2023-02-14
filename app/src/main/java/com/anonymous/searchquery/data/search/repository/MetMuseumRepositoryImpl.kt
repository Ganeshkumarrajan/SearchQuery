package com.anonymous.searchquery.data.search.repository

import com.anonymous.searchquery.data.api.MetMuseumService
import com.anonymous.searchquery.data.details.model.ObjectDetailsNetwork
import com.anonymous.searchquery.data.search.model.SearchNetwork
import com.anonymous.searchquery.data.utils.NetworkToDomainMapper
import com.anonymous.searchquery.data.utils.convertToNetworkResult
import com.anonymous.searchquery.domain.details.model.ObjectDetailsDomain
import com.anonymous.searchquery.domain.search.model.SearchDomain
import com.anonymous.searchquery.domain.search.repository.MetMuseumRepository
import com.anonymous.searchquery.domain.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MetMuseumRepositoryImpl(
    private val service: MetMuseumService,
    private val mapper: NetworkToDomainMapper<SearchNetwork, List<SearchDomain?>>,
    private val dispatcher: CoroutineDispatcher,
    private val objectDetailsMapper: NetworkToDomainMapper<ObjectDetailsNetwork, ObjectDetailsDomain>,
) : MetMuseumRepository {

    override suspend fun doSearch(query: String): Flow<NetworkResult<List<SearchDomain?>>> = flow {
        emit(convertToNetworkResult(service.searchQuery(query), mapper))
    }.flowOn(dispatcher)

    override fun getDetails(objectId: Long): Flow<NetworkResult<ObjectDetailsDomain>> = flow {
        emit(convertToNetworkResult(service.objectDetails(objectId), objectDetailsMapper))
    }
}

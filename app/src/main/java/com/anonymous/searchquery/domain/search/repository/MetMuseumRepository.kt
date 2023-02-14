package com.anonymous.searchquery.domain.search.repository

import com.anonymous.searchquery.domain.details.model.ObjectDetailsDomain
import com.anonymous.searchquery.domain.search.model.SearchDomain
import com.anonymous.searchquery.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MetMuseumRepository {
    suspend fun doSearch(query: String): Flow<NetworkResult<List<SearchDomain?>>>
    fun getDetails(objectId: Long): Flow<NetworkResult<ObjectDetailsDomain>>
}

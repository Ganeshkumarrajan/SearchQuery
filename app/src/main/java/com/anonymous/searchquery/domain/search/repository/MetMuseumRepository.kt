package com.anonymous.searchquery.domain.search.repository

import com.anonymous.searchquery.data.base.NetworkResult
import com.anonymous.searchquery.domain.search.usecase.SearchDomain
import kotlinx.coroutines.flow.Flow

interface MetMuseumRepository {
    suspend fun doSearch(query: String): Flow<NetworkResult<List<SearchDomain?>>>
}

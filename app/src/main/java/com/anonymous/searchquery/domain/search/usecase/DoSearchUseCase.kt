package com.anonymous.searchquery.domain.search.usecase

import com.anonymous.searchquery.data.base.NetworkResult
import com.anonymous.searchquery.domain.search.repository.MetMuseumRepository
import kotlinx.coroutines.flow.Flow

interface DoSearchUseCase {
    suspend fun doSearch(query: String): Flow<NetworkResult<List<SearchDomain?>>>
}
class DoSearchUseCaseImpl(private val metMuseumRepository: MetMuseumRepository) : DoSearchUseCase {
    override suspend fun doSearch(query: String): Flow<NetworkResult<List<SearchDomain?>>> =
        metMuseumRepository.doSearch(query)
}

data class SearchDomain(val objectId: Long)

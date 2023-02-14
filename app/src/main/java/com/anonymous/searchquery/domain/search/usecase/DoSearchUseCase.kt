package com.anonymous.searchquery.domain.search.usecase

import com.anonymous.searchquery.domain.search.model.SearchDomain
import com.anonymous.searchquery.domain.search.repository.MetMuseumRepository
import com.anonymous.searchquery.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface DoSearchUseCase {
    suspend fun doSearch(query: String): Flow<NetworkResult<List<SearchDomain?>>>
}
class DoSearchUseCaseImpl(private val metMuseumRepository: MetMuseumRepository) : DoSearchUseCase {
    override suspend fun doSearch(query: String): Flow<NetworkResult<List<SearchDomain?>>> =
        metMuseumRepository.doSearch(query)
}

package com.anonymous.searchquery.domain.details.usecase

import com.anonymous.searchquery.domain.details.model.ObjectDetailsDomain
import com.anonymous.searchquery.domain.search.repository.MetMuseumRepository
import com.anonymous.searchquery.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface GetObjectDetailsUseCase {
    fun getDetails(id: Long): Flow<NetworkResult<ObjectDetailsDomain>>
}

class GetObjectDetailsUseCaseImpl(val repository: MetMuseumRepository) : GetObjectDetailsUseCase {
    override fun getDetails(id: Long): Flow<NetworkResult<ObjectDetailsDomain>> =
        repository.getDetails(id)
}

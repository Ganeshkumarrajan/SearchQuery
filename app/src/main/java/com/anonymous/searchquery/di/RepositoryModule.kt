package com.anonymous.searchquery.di

import com.anonymous.searchquery.data.api.MetMuseumService
import com.anonymous.searchquery.data.details.mapper.ObjectDetailsNetworkToDomainMapper
import com.anonymous.searchquery.data.details.model.ObjectDetailsNetwork
import com.anonymous.searchquery.data.search.mapper.SearchNetworkToMapper
import com.anonymous.searchquery.data.search.model.SearchNetwork
import com.anonymous.searchquery.data.search.repository.MetMuseumRepositoryImpl
import com.anonymous.searchquery.data.utils.NetworkToDomainMapper
import com.anonymous.searchquery.domain.details.model.ObjectDetailsDomain
import com.anonymous.searchquery.domain.search.model.SearchDomain
import com.anonymous.searchquery.domain.search.repository.MetMuseumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    fun provideSearchMapper(): NetworkToDomainMapper<SearchNetwork, List<SearchDomain?>> =
        SearchNetworkToMapper()

    @Provides
    fun provideRepository(
        service: MetMuseumService,
        searchMapper: NetworkToDomainMapper<SearchNetwork, List<SearchDomain?>>,
        objectDetailsMapper: NetworkToDomainMapper<ObjectDetailsNetwork, ObjectDetailsDomain>
    ): MetMuseumRepository =
        MetMuseumRepositoryImpl(service, searchMapper, Dispatchers.IO, objectDetailsMapper)

    @Provides
    fun provideObjectDetailNetworkMapper(): NetworkToDomainMapper<ObjectDetailsNetwork, ObjectDetailsDomain> =
        ObjectDetailsNetworkToDomainMapper()
}

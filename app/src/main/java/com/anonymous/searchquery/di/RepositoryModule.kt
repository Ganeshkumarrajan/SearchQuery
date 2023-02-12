package com.anonymous.searchquery.di

import com.anonymous.searchquery.data.SearchNetworkToMapper
import com.anonymous.searchquery.data.api.MetMuseumService
import com.anonymous.searchquery.data.search.mapper.NetworkToDomainMapper
import com.anonymous.searchquery.data.search.model.SearchNetwork
import com.anonymous.searchquery.data.search.repository.MetMuseumRepositoryImpl
import com.anonymous.searchquery.domain.search.repository.MetMuseumRepository
import com.anonymous.searchquery.domain.search.usecase.SearchDomain
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
        searchMapper: NetworkToDomainMapper<SearchNetwork, List<SearchDomain?>>
    ): MetMuseumRepository =
        MetMuseumRepositoryImpl(service, searchMapper, Dispatchers.IO)
}

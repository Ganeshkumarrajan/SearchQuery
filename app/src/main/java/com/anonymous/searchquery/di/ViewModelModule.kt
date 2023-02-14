package com.anonymous.searchquery.di

import android.content.Context
import com.anonymous.searchquery.domain.details.model.ObjectDetailsDomain
import com.anonymous.searchquery.domain.search.model.SearchDomain
import com.anonymous.searchquery.presentaiton.details.model.ObjectDetailsUI
import com.anonymous.searchquery.presentaiton.details.viewmodel.ObjectDetailsMapperDomainToUI
import com.anonymous.searchquery.presentaiton.search.mapper.DomainToUIMapper
import com.anonymous.searchquery.presentaiton.search.mapper.SearchResultMapperDomainToUI
import com.anonymous.searchquery.presentaiton.search.model.SearchUI
import com.anonymous.searchquery.presentaiton.util.ResourceManager
import com.anonymous.searchquery.presentaiton.util.ResourceManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ViewModelModule {
    @Provides
    fun provideSearchUIMapper(): DomainToUIMapper<List<SearchDomain?>, List<SearchUI>> =
        SearchResultMapperDomainToUI()

    @Singleton
    @Provides
    fun provideResourceManger(@ApplicationContext appContext: Context): ResourceManager =
        ResourceManagerImpl(appContext)

    @Provides
    fun provideObjectDetailsUIMapper(resourceManager: ResourceManager): DomainToUIMapper<ObjectDetailsDomain, ObjectDetailsUI> =
        ObjectDetailsMapperDomainToUI(resourceManager)
}

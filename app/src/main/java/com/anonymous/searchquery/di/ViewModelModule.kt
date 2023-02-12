package com.anonymous.searchquery.di

import android.content.Context
import com.anonymous.searchquery.domain.search.usecase.SearchDomain
import com.anonymous.searchquery.presentaiton.search.model.SearchUI
import com.anonymous.searchquery.presentaiton.search.viewmodel.DomainToUIMapper
import com.anonymous.searchquery.presentaiton.search.viewmodel.SearchResultMapperDomainToUI
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
}

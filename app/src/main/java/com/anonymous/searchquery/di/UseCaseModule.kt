package com.anonymous.searchquery.di

import com.anonymous.searchquery.domain.search.repository.MetMuseumRepository
import com.anonymous.searchquery.domain.search.usecase.DoSearchUseCase
import com.anonymous.searchquery.domain.search.usecase.DoSearchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Provides
    fun provideSearchUseCase(metMuseumRepository: MetMuseumRepository): DoSearchUseCase =
        DoSearchUseCaseImpl(metMuseumRepository)
}

package com.anonymous.searchquery.di

import com.anonymous.searchquery.BuildConfig
import com.anonymous.searchquery.data.api.MetMuseumService
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().apply {
            this.baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
            this.client(httpClient)
        }.build()

    @Singleton
    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            this.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    @Singleton
    @Provides
    fun provideOKHTTPClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

    @Singleton
    @Provides
    fun provideMetMuseumService(retrofit: Retrofit): MetMuseumService =
        retrofit.create(MetMuseumService::class.java)
}

package com.willwong.newsheadlines.ui.di.module

import com.willwong.newsheadlines.data.network.NewsApiService
import com.willwong.newsheadlines.data.network.RetrofitClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiServiceModule {
    @Singleton
    @Provides
    fun provideApiClient(): NewsApiService {
        return RetrofitClient.client().create(NewsApiService::class.java)
    }
}
package com.willwong.newsheadlines.ui.di.module

import com.willwong.newsheadlines.data.DataSource
import com.willwong.newsheadlines.data.local.LocalDataPersistence
import com.willwong.newsheadlines.data.local.LocalNewsDataSource
import com.willwong.newsheadlines.data.network.NewsApiService
import com.willwong.newsheadlines.data.network.NewsDataSource
import com.willwong.newsheadlines.ui.di.Local
import com.willwong.newsheadlines.ui.di.Remote
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {


    @Remote
    @Provides
    @Singleton
    fun provideNetworkDataSource(service: NewsApiService) : DataSource {
        return NewsDataSource(service)
    }


    @Local
    @Provides
    @Singleton
    fun provideLocalDataSource() : DataSource{
        return LocalNewsDataSource()
    }



    @Local
    @Provides
    @Singleton
    fun provideLocalDataPersistence() : LocalDataPersistence {
        return LocalDataPersistence()
    }

}
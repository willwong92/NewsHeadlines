package com.willwong.newsheadlines.ui.di.module

import android.app.Application
import android.content.Context
import com.willwong.newsheadlines.App
import com.willwong.newsheadlines.ui.di.scope.PerApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: App) {

    @Provides
    @Singleton
    fun provideApplication(): App{
        return baseApp
    }

    @Provides
    @Singleton
    fun provideContext() : Context {
        return baseApp.applicationContext
    }
}
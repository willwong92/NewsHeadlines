package com.willwong.newsheadlines.ui.di.component

import com.willwong.newsheadlines.App
import com.willwong.newsheadlines.ui.di.module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, RepositoryModule::class, ApiServiceModule::class))
interface ApplicationComponent {
    fun add(module : ArticlePresenterModule) : ArticleComponent

    fun add(module : HeadlinesPresenterModule) : HeadlinesComponent

}
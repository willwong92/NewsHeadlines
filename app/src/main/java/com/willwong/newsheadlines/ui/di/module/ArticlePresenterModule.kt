package com.willwong.newsheadlines.ui.di.module

import com.willwong.newsheadlines.ui.di.GlobalScope
import com.willwong.newsheadlines.ui.presenter.ArticleContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ArticlePresenterModule(private var view: ArticleContract.View) {

    @Provides
    @GlobalScope
    fun provideView() : ArticleContract.View {
        return view
    }
}
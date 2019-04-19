package com.willwong.newsheadlines.ui.di.module

import com.willwong.newsheadlines.data.network.NewsApiService
import com.willwong.newsheadlines.data.network.RetrofitClient
import com.willwong.newsheadlines.ui.di.GlobalScope
import com.willwong.newsheadlines.ui.presenter.HeadlinesContract
import dagger.Module
import dagger.Provides

@Module
class HeadlinesPresenterModule(private var view: HeadlinesContract.View) {
    @GlobalScope
    @Provides
    fun provideView() : HeadlinesContract.View{
        return view
    }

}


package com.willwong.newsheadlines.data

import android.util.Log
import com.vicpin.krealmextensions.saveAll
import com.willwong.newsheadlines.data.local.LocalDataPersistence
import com.willwong.newsheadlines.data.local.LocalNewsDataSource
import com.willwong.newsheadlines.data.model.Article
import com.willwong.newsheadlines.data.network.NewsDataSource
import com.willwong.newsheadlines.ui.di.Local
import com.willwong.newsheadlines.ui.di.Remote
import io.reactivex.Observable
import io.reactivex.Observable.just
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(@Local localDataSource: DataSource, @Remote networkDataSource: DataSource, localDataPersistence: LocalDataPersistence) {
    private var localDataSource : DataSource
    private var networkDataSource : DataSource
    private var localDataPersistence : DataPersistence
    init {
        this.localDataSource = localDataSource
        this.networkDataSource = networkDataSource
        this.localDataPersistence = localDataPersistence
    }

    fun getArticles() : Observable<List<Article>> {
        return getLocalArticles().toObservable()
                .onErrorResumeNext( getNetworkArticles())
    }
    fun getLocalArticles(): Single<List<Article>> {
        return localDataSource.getNewsList()
    }

    fun getNetworkArticles() : Observable<List<Article>> {
        return networkDataSource.getNewsList().toObservable().doOnNext( { articles ->
                localDataPersistence.save(articles)
        })
    }
    fun getArticle(name : String) : Observable<Article>? {
        return localDataSource.getNewsArticle(name).toObservable()
    }

}
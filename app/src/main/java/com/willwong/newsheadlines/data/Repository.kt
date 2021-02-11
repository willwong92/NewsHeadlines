package com.willwong.newsheadlines.data

import com.willwong.newsheadlines.data.local.LocalDataPersistence
import com.willwong.newsheadlines.data.model.Article
import com.willwong.newsheadlines.ui.di.Local
import com.willwong.newsheadlines.ui.di.Remote
import io.reactivex.Observable
import io.reactivex.Single
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
package com.willwong.newsheadlines.data.network

import com.willwong.newsheadlines.data.DataSource
import com.willwong.newsheadlines.data.model.Article
import com.willwong.newsheadlines.data.model.ArticleResponse
import com.willwong.newsheadlines.ui.di.Remote
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Singleton;
import javax.inject.Inject;
/**
 * Remote data source for News.
 */

@Singleton
class NewsDataSource  @Inject constructor(service: NewsApiService) : DataSource {
    private val service : NewsApiService
    init {
        this.service = service
    }
    override fun getNewsList(): Single<List<Article>> {
        return service.getHeadLines()
                .map {t: ArticleResponse -> t.getArticles()  }

    }
    //Return nothing because API does not support single queries.
    override fun getNewsArticle(name: String): Maybe<Article> {
        return Maybe.empty()
    }



}
package com.willwong.newsheadlines.data.local

import com.vicpin.krealmextensions.query
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.queryFirst
import com.willwong.newsheadlines.data.DataSource
import com.willwong.newsheadlines.data.model.Article
import com.willwong.newsheadlines.ui.di.Local
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalNewsDataSource  @Inject constructor(): DataSource {
    override fun getNewsList(): Single<List<Article>> {
        return Single.just(findArticles())
    }
    fun findArticles() : List<Article> {
        val realm = Article().queryAll()
        return realm
    }

    override fun getNewsArticle(name : String): Maybe<Article> {
        val article : Article? = findArticleByName(name)
        return Maybe.just(article)
    }

    fun findArticleByName(name : String) : Article? {
        val realm : Article? = Article().queryFirst{equalTo("source.name", name)}
        return realm
    }



}
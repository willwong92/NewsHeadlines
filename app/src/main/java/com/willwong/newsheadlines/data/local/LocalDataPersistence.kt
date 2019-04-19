package com.willwong.newsheadlines.data.local

import com.vicpin.krealmextensions.*
import com.willwong.newsheadlines.data.DataPersistence
import com.willwong.newsheadlines.data.model.Article
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataPersistence @Inject constructor(): DataPersistence {
    // saves article in local realm database
    override fun save(articles: List<Article>) {
        // finds article in database if it does not exist create a record in database for it
        executeTransaction {realm ->
            for (article in articles) {
                val articleRealm = realm.createObject<Article>()
                articleRealm.authorName = article.authorName
                articleRealm.publishedAt = article.publishedAt
                articleRealm.urlImage = article.urlImage
                articleRealm.url = article.url
                articleRealm.description = article.description
                articleRealm.titleName = article.titleName
                articleRealm.content = article.content
                articleRealm.source!!.name = article.source!!.name
            }
        }

    }

}
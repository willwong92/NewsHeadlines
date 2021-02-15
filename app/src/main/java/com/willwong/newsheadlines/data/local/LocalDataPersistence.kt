package com.willwong.newsheadlines.data.local

import com.vicpin.krealmextensions.*
import com.willwong.newsheadlines.data.DataPersistence
import com.willwong.newsheadlines.data.model.Article
import io.realm.kotlin.createObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataPersistence @Inject constructor(): DataPersistence {


    // saves article in local realm database
    override fun save(articlesList: List<Article>) {
        // finds article in database if it does not exist create a record in database for it
        executeTransaction {realm ->
            for (article in articlesList) {
                realm.createObject<Article>().also {
                    it.authorName = article.authorName
                    it.publishedAt = article.publishedAt
                    it.urlImage = article.urlImage
                    it.url = article.url
                    it.description = article.description
                    it.titleName = article.titleName
                    it.content = article.content
                    it.source?.name = article.source?.name
                    realm.insert(it)
                }
            }

        }

    }

}
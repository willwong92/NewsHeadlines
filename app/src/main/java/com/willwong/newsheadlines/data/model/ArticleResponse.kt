package com.willwong.newsheadlines.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by WillWong on 3/25/19.
 */
class ArticleResponse {
    @Expose
    @SerializedName("articles")
    private var articles = ArrayList<Article>()

    fun getArticles(): ArrayList<Article> {
        return articles
    }
    fun setArticles(newArticlesList: ArrayList<Article>) {
        articles = newArticlesList
    }
}
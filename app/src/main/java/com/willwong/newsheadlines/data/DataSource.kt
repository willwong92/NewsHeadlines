package com.willwong.newsheadlines.data

import com.willwong.newsheadlines.data.model.Article
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by WillWong on 4/7/19.
 */
interface DataSource {

    fun getNewsList() : Single<List<Article>>

    fun getNewsArticle(name : String) : Maybe<Article>


}
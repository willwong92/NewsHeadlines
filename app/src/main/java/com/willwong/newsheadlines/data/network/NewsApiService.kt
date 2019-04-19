package com.willwong.newsheadlines.data.network

import com.willwong.newsheadlines.data.model.ArticleResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by WillWong on 3/26/19.
 */
interface NewsApiService {
    @GET("v2/top-headlines?country=us")
    fun getHeadLines() : Single<ArticleResponse>

}
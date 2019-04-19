package com.willwong.newsheadlines.data.network

import com.willwong.newsheadlines.BuildConfig

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by WillWong on 3/26/19.
 */

class RequestInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url()

        val newUrl = originalUrl.newBuilder()
                .addQueryParameter("apiKey", BuildConfig.newsapi_key)
                .build()

        val requestBuilder = original.newBuilder().url(newUrl)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}

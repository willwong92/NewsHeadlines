package com.willwong.newsheadlines.data.network

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import io.realm.RealmObject
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by WillWong on 3/25/19.
 */
@Singleton
class RetrofitClient() {

    companion object {

        private val BASE_URL = "https://newsapi.org/"
        //Data de-serializing for realm objects.
        //Required to correctly map to realm when using Retrofit2 with Gson
        val gson = GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {

            override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                return false
            }

            override fun shouldSkipField(f: FieldAttributes?): Boolean {
                return f?.declaredClass == RealmObject::class.java
            }

        }).create()
        fun client(): Retrofit {
                val interceptor = RequestInterceptor()
                val client = OkHttpClient.Builder()
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .addInterceptor(interceptor)
                        .build();
                val retrofit = Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .baseUrl(BASE_URL)
                        .client(client)
                        .build()


            return retrofit
        }
    }
}
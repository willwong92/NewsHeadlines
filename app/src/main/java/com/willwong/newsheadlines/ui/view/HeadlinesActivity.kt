package com.willwong.newsheadlines.ui.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.willwong.newsheadlines.R
import com.willwong.newsheadlines.data.model.ArticleResponse
import com.willwong.newsheadlines.data.network.NewsApiService
import com.willwong.newsheadlines.data.network.RetrofitClient

import kotlinx.android.synthetic.main.activity_headslines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeadlinesActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headslines)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(container.id, HeadlinesFragment.newInstance(),HeadlinesFragment::class.java.simpleName)
                    .commit()
        }


    }








}

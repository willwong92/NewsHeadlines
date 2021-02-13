package com.willwong.newsheadlines.ui.presenter

import com.willwong.newsheadlines.data.model.Article
import com.willwong.newsheadlines.ui.base.BasePresenter
import com.willwong.newsheadlines.ui.base.BaseView
import javax.annotation.Nullable

/**
 * Created by WillWong on 4/5/19.
 */
interface HeadlinesContract {

    interface Presenter : BasePresenter<View> {
        fun fetchArticleList(showLoading: Boolean)


        fun navigateToArticleDetails(article : Article)
    }

    @Nullable
    interface View : BaseView{
        fun showArticleList(articleList: List<Article> ) {

        }
        fun showArticleDetails(name : String) {

        }
        fun setProgressBar(progress: Boolean) {

        }


    }

}
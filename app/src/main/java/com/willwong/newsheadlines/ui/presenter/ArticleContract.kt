package com.willwong.newsheadlines.ui.presenter

import android.view.View
import com.willwong.newsheadlines.ui.base.BasePresenter
import com.willwong.newsheadlines.ui.base.BaseView

class ArticleContract {
    interface Presenter : BasePresenter<View> {
        fun fetchArticleDetails(name : String)
    }

    interface View : BaseView {
        fun showAuthor(author : String)

        fun showTitle(title : String)

        fun showUrl(url : String)

        fun showUrlToImage(image : String)

        fun showPublishedDate(date : String)

        fun showLoading(loading : Boolean)

        fun setTitleAppBar(appBarTitle : String)

        fun setSubTitleAppBar(subBarTitle : String)
    }
}
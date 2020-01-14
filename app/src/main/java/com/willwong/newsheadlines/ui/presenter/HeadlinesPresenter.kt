package com.willwong.newsheadlines.ui.presenter

import android.view.View
import com.willwong.newsheadlines.data.Repository
import com.willwong.newsheadlines.data.model.Article
import com.willwong.newsheadlines.ui.base.BasePresenter
import com.willwong.newsheadlines.ui.base.BaseView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by WillWong on 4/3/19.
 */
class HeadlinesPresenter @Inject constructor(repository : Repository, private var view : HeadlinesContract.View) : HeadlinesContract.Presenter{
    private val repository : Repository
    private var compositeDisposible : CompositeDisposable
    init {
        this.repository = repository
        compositeDisposible = CompositeDisposable()
    }


    override fun fetchArticleList(showLoading: Boolean) {
        view.setProgressBar(showLoading)
        getArticles()
    }

    override fun navigateToArticleDetails(article: Article) {
        view.showArticleDetails(article.source?.name!!)
    }

    override fun onAttach(v: HeadlinesContract.View) {
        view = v
    }

    override fun onDetach() {
        compositeDisposible.clear()
    }
    private fun getArticles() {
        val disposable : Disposable = repository.getNetworkArticles()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe( { list ->
                    view.showArticleList(list)
                }, {
                    error ->
                    print("Headlines Presenter: " + error.printStackTrace())
                })
        compositeDisposible.add(disposable)
    }


}
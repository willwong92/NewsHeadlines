package com.willwong.newsheadlines.ui.presenter

import com.willwong.newsheadlines.data.Repository
import com.willwong.newsheadlines.data.model.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticlePresenter @Inject constructor(repository : Repository, private var view : ArticleContract.View?) : ArticleContract.Presenter {
    private val repository : Repository
    private val compositeDisposable : CompositeDisposable
    init {
        this.repository = repository
        compositeDisposable = CompositeDisposable()
    }

    override fun onAttach(v: ArticleContract.View) {
        view = v
    }

    override fun onDetach() {
        view = null
        compositeDisposable.clear()
    }

    override fun fetchArticleDetails(name: String) {
        view?.showLoading(true)
        val disposable : Disposable? = repository.getArticle(name)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    article ->
                    view?.showLoading(true)
                    showArticleDetails(article)
                }, {
                    throwable ->
                    view?.showLoading(false)
                    throwable.stackTrace
                })

        compositeDisposable.add(disposable!!)
    }

    private fun showArticleDetails(article : Article) {
        view?.showAuthor(article.authorName!!)
        view?.showTitle(article.titleName!!)
        view?.setTitleAppBar(article.titleName!!)
        view?.setSubTitleAppBar(article.url!!)
        view?.showPublishedDate(article.publishedAt!!)
        view?.showUrl(article.url!!)
        view?.showUrlToImage(article.urlImage!!)
    }
}
package com.willwong.newsheadlines.ui.view

import android.databinding.DataBindingUtil;
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.Toast
import com.willwong.newsheadlines.App
import com.willwong.newsheadlines.R
import com.willwong.newsheadlines.data.model.Article
import com.willwong.newsheadlines.databinding.HeadlinesBinding
import com.willwong.newsheadlines.ui.adapter.HeadlinesAdapter
import com.willwong.newsheadlines.ui.base.BaseFragement
import com.willwong.newsheadlines.ui.di.module.HeadlinesPresenterModule
import com.willwong.newsheadlines.ui.presenter.HeadlinesContract
import com.willwong.newsheadlines.ui.presenter.HeadlinesPresenter
import kotlinx.android.synthetic.main.fragment_headlines_list.*
import kotlinx.android.synthetic.main.fragment_headlines_list.view.*
import javax.inject.Inject

/**
 *
 */
class HeadlinesFragment : BaseFragement(), HeadlinesContract.View, HeadlinesAdapter.onItemClickListener{

    private val PAGESTART = 1
    private var currentPage = PAGESTART
    private var isLastPage = false
    private var totalPage = 10
    private var isLoading = false
    private var itemCount = 0;
    private var dataBinding : HeadlinesBinding? = null
    private var adapter : HeadlinesAdapter? = null
    @Inject
    lateinit var presenter: HeadlinesPresenter
    companion object {
        fun newInstance(): Fragment = HeadlinesFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        injectDependency()
        presenter.onAttach(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_headlines_list, container, false)
        return dataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        presenter.fetchArticleList(true)


    }

    fun initRecyclerView() {
        if (adapter == null)
            adapter = HeadlinesAdapter(this)

        dataBinding?.recyclerView?.adapter = adapter
        dataBinding?.recyclerView?.layoutManager = LinearLayoutManager(App.instance.applicationContext)

    }
    //TODO unfinished implementation for pagination
    fun onRefresh() {
        itemCount = 0
        currentPage = PAGESTART
        isLastPage = false
        adapter?.clear()
        //prepa
    }
    fun initSwipeRefresh() {
        dataBinding?.refreshSwipe?.setOnRefreshListener{

            refresh_swipe.isRefreshing = false;
        }
    }

    override fun setProgressBar(progress: Boolean) {
        val visibility = if (progress) View.VISIBLE else View.GONE
        dataBinding?.barProgress?.visibility = visibility
    }

    override fun showArticleDetails(name: String) {
        startFragment(ArticleFragment.newInstance(name))
    }

    override fun showArticleList(articleList: List<Article>) {
        adapter?.setArticles(articleList)
        setProgressBar(false)
    }

    override fun onItemClicked(article: Article) {
        presenter.navigateToArticleDetails(article)
    }
    private fun injectDependency() = App.instance.getApplicationComponent().add(HeadlinesPresenterModule(this))
            .inject(this)

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
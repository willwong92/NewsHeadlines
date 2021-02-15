package com.willwong.newsheadlines.ui.view

import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.willwong.newsheadlines.App
import com.willwong.newsheadlines.R
import com.willwong.newsheadlines.ui.base.BaseFragement
import com.willwong.newsheadlines.databinding.FragmentArticleBinding
import com.willwong.newsheadlines.ui.di.module.ArticlePresenterModule
import com.willwong.newsheadlines.ui.presenter.ArticleContract
import com.willwong.newsheadlines.ui.presenter.ArticlePresenter
import kotlinx.android.synthetic.main.fragment_article.*
import javax.inject.Inject





class ArticleFragment : BaseFragement(), ArticleContract.View, AppBarLayout.OnOffsetChangedListener {
    private var binding : FragmentArticleBinding? = null
    private var name : String? = null
    private var isHideToolBar : Boolean = false
    @Inject
    lateinit var presenter : ArticlePresenter
    companion object {
        private val FRAGMENT_ARTICLE_ID = "article_id"

        fun newInstance(name: String): ArticleFragment {
            val args = Bundle()
            args.putString(FRAGMENT_ARTICLE_ID, name)
            val fragment = ArticleFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            if (it is HeadlinesActivity) {
                it.supportActionBar?.hide()
            }
        }

        retainInstance = true

        injectDependency()

        presenter.onAttach(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = getArticleName(savedInstanceState)

        setupToolbar()

        fetchArticle(name!!)
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(FRAGMENT_ARTICLE_ID, name)
        super.onSaveInstanceState(outState)
    }

    private fun getArticleName(savedInstanceState: Bundle?): String {
        return savedInstanceState?.getString(FRAGMENT_ARTICLE_ID, null)
                ?: arguments!!.getString(FRAGMENT_ARTICLE_ID, null)
    }
    private fun fetchArticle(name : String) {
        presenter.fetchArticleDetails(name)
    }

    private fun retrieveArticleSource(savedInstanceState: Bundle?) : String?{
        return savedInstanceState?.getString(FRAGMENT_ARTICLE_ID, null) ?:
                arguments!!.getString(FRAGMENT_ARTICLE_ID, null)
    }

    private fun injectDependency() =
        App.instance.getApplicationComponent().add(ArticlePresenterModule(this)).inject(this)



    private fun setupToolbar() {
        binding?.toolbar?.setNavigationOnClickListener(View.OnClickListener { activity!!.supportFragmentManager.popBackStack() })
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun showAuthor(author: String) {

    }

    override fun showLoading(loading: Boolean) {
        binding?.progressBar?.visibility = if (loading) View.VISIBLE else View.INVISIBLE
    }

    override fun showPublishedDate(date: String) {
        binding?.date?.setText(date)
    }

    override fun showTitle(title: String) {
        binding?.title?.setText(title)
    }

    override fun setSubTitleAppBar(subBarTitle: String) {
        binding?.subtitleOnAppbar?.setText(subBarTitle)
    }

    override fun setTitleAppBar(appBarTitle: String) {
        binding?.titleOnAppbar?.setText(appBarTitle)
    }


    override fun showUrl(url: String) {
        binding?.webView?.settings?.loadsImagesAutomatically = true
        binding?.webView?.settings?.javaScriptEnabled = true
        binding?.webView?.settings?.setSupportZoom(true)
        binding?.webView?.settings?.domStorageEnabled = true
        binding?.webView?.settings?.builtInZoomControls = true
        binding?.webView?.settings?.displayZoomControls = false
        binding?.webView?.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        binding?.webView?.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showLoading(true)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                showLoading(false)
            }
        }
        binding?.webView?.loadUrl(url)
    }

    override fun showUrlToImage(image: String) {
        Glide.with(this).load(image).transition(DrawableTransitionOptions.withCrossFade()).into(binding!!.backdrop)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val maxScroll = appBarLayout?.totalScrollRange
        val percent = Math.abs(verticalOffset / maxScroll as Float)

        if (percent == 1f && isHideToolBar) {
            binding?.titleAppbar?.visibility = View.VISIBLE
            binding?.dateBehavior?.visibility = View.GONE
            isHideToolBar = false
        }
        else if (percent < 1f && !isHideToolBar)
            binding?.titleAppbar?.visibility = View.GONE
            binding?.dateBehavior?.visibility = View.VISIBLE
            isHideToolBar = true
    }


}
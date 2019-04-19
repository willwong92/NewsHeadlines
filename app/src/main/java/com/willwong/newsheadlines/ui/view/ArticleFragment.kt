package com.willwong.newsheadlines.ui.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.willwong.newsheadlines.App
import com.willwong.newsheadlines.R
import com.willwong.newsheadlines.data.model.Article
import com.willwong.newsheadlines.ui.base.BaseFragement
import com.willwong.newsheadlines.databinding.FragmentArticleBinding
import com.willwong.newsheadlines.ui.di.module.ArticlePresenterModule
import com.willwong.newsheadlines.ui.presenter.ArticleContract
import com.willwong.newsheadlines.ui.presenter.ArticlePresenter
import kotlinx.android.synthetic.main.fragment_article.*
import javax.inject.Inject





class ArticleFragment : BaseFragement(), ArticleContract.View {
    private var binding : FragmentArticleBinding? = null
    private var name : String? = null
    @Inject
    lateinit var presenter : ArticlePresenter
    companion object {
        private val FRAGMENT_ARTICLE_ID = "article_id"

        fun newInstance(name: String): ArticleFragment {
            val args: Bundle = Bundle()
            args.putString(FRAGMENT_ARTICLE_ID, name)
            val fragment = ArticleFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun showContent(content: String) {

    }

    override fun showDescription(desc: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading(loading: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showPublishedDate(date: String) {
        binding?.date?.setText(date)
    }

    override fun showTitle(title: String) {
        binding?.title?.setText(title)
    }

    override fun showUrl(url: String) {

    }

    override fun showUrlToImage(image: String) {
        Glide.with(this).load(image).transition(DrawableTransitionOptions.withCrossFade()).into(binding!!.backdrop)
    }


}
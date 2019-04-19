package com.willwong.newsheadlines.ui.di.component

import com.willwong.newsheadlines.ui.di.GlobalScope
import com.willwong.newsheadlines.ui.di.module.ArticlePresenterModule
import com.willwong.newsheadlines.ui.view.ArticleFragment
import dagger.Subcomponent

@GlobalScope
@Subcomponent(modules = arrayOf(ArticlePresenterModule::class))
interface ArticleComponent {
    fun inject(fragment: ArticleFragment)
}
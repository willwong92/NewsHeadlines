package com.willwong.newsheadlines.ui.di.component

import com.willwong.newsheadlines.ui.di.GlobalScope
import com.willwong.newsheadlines.ui.di.module.HeadlinesPresenterModule
import com.willwong.newsheadlines.ui.view.HeadlinesFragment
import dagger.Subcomponent
@GlobalScope
@Subcomponent(modules = arrayOf(HeadlinesPresenterModule::class))
interface HeadlinesComponent {
    fun inject(fragment : HeadlinesFragment)
}
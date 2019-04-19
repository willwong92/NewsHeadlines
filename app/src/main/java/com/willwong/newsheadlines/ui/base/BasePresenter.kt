package com.willwong.newsheadlines.ui.base

import android.view.View

/**
 * Base class for presenters
 */
interface BasePresenter<View> {
    fun onAttach(v : View)
    fun onDetach()
}
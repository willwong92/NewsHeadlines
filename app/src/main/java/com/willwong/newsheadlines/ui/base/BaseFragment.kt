package com.willwong.newsheadlines.ui.base

import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_headslines.*

/**
 * Base class for fragments
 */
open class BaseFragement  : Fragment() {

    fun startFragment(fragment : Fragment) {
        activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(container.id, fragment, fragment.javaClass.simpleName)
                ?.addToBackStack(null)
                ?.commit()
    }

}
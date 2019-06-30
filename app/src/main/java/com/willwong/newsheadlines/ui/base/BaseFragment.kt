package com.willwong.newsheadlines.ui.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.willwong.newsheadlines.R
import kotlinx.android.synthetic.main.activity_headslines.*

/**
 * Base class for fragments
 */
open class BaseFragement  : Fragment() {

    fun startFragment(fragment : Fragment) {



        val manager = activity!!.supportFragmentManager

                val transaction = manager.beginTransaction()

                transaction
                ?.replace(R.id.container, fragment, fragment.javaClass.simpleName)
                ?.addToBackStack(null)
                ?.commit()
    }

}
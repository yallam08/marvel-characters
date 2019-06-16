package com.yallam.marvelapp.base

import android.content.Context
import androidx.fragment.app.Fragment

/**
 * Created by Yahia Allam on 15/06/2019
 */
open class BaseFragment : Fragment() {

    lateinit var fragmentNavigator: FragmentNavigator

    override fun onAttach(context: Context) {
        try {
            fragmentNavigator = activity as FragmentNavigator
        } catch (e: ClassCastException) {
            throw ClassCastException(
                "${activity!!::class.simpleName} must implement ${FragmentNavigator::class.simpleName}"
            )
        }

        return super.onAttach(context)
    }
}
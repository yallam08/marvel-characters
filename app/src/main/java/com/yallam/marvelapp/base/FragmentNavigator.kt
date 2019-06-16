package com.yallam.marvelapp.base

import androidx.fragment.app.Fragment

/**
 * Created by Yahia Allam on 15/06/2019
 */
interface FragmentNavigator {

    fun navigateToFragment(fragment: Fragment)

    fun navigateToFragmentSaveState(currentFragment: Fragment, newFragment: Fragment)

}
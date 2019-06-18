package com.yallam.marvelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.yallam.marvelapp.base.FragmentNavigator
import com.yallam.marvelapp.presentation.characterslist.CharactersListFragment

class MainActivity : AppCompatActivity(), FragmentNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(CharactersListFragment(), addToBackStack = false)
    }

    override fun navigateToFragment(fragment: Fragment) {
        replaceFragment(fragment)
    }

    override fun navigateToFragmentSaveState(currentFragment: Fragment, newFragment: Fragment) {
        addFragment(currentFragment, newFragment)
    }

    private fun addFragment(currentFragment: Fragment, newFragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .hide(currentFragment)
            .add(R.id.fragment_container, newFragment, newFragment::class.simpleName)
            .addToBackStack(null)
            .commit()
    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_container, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }
}

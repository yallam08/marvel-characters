package com.yallam.marvelapp.base

import android.app.Application
import com.yallam.marvelapp.data.di.dataModule
import com.yallam.marvelapp.data.di.remoteModule
import com.yallam.marvelapp.data.di.rxModule
import com.yallam.marvelapp.presentation.di.viewModelModule
import org.koin.android.ext.android.startKoin

/**
 * Created by Yahia Allam on 15/06/2019
 */
class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin(
            this,
            listOf(
                dataModule,
                remoteModule,
                rxModule,
                viewModelModule
            )
        )
    }
}
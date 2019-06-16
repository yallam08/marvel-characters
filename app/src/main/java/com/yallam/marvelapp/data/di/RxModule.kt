package com.yallam.marvelapp.data.di

import com.yallam.marvelapp.base.schedulers.RxThreadSchedulers
import com.yallam.marvelapp.base.schedulers.ThreadSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module.module

/**
 * Created by Yahia Allam on 16/06/2019
 */
val rxModule = module {
    factory { CompositeDisposable() }
    factory<ThreadSchedulers> { RxThreadSchedulers() }
}
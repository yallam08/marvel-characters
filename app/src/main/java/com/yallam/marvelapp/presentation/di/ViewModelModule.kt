package com.yallam.marvelapp.presentation.di

import com.yallam.marvelapp.presentation.characterslist.CharactersListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by Yahia Allam on 15/06/2019
 */
val viewModelModule = module {

    viewModel {
        CharactersListViewModel(
            charactersRepository = get(),
            threadSchedulers = get(),
            compositeDisposable = get()
        )
    }
}
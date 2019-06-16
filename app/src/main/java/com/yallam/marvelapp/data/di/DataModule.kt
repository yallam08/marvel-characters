package com.yallam.marvelapp.data.di

import com.yallam.marvelapp.data.CharactersRepository
import org.koin.dsl.module.module

/**
 * Created by Yahia Allam on 15/06/2019
 */
val dataModule = module {

    factory { CharactersRepository(apiEndpoints = get(), charactersDao = get()) }
}
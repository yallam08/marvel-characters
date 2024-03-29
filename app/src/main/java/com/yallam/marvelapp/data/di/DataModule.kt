package com.yallam.marvelapp.data.di

import com.yallam.marvelapp.data.CharactersRepository
import com.yallam.marvelapp.data.ICharactersRepository
import org.koin.dsl.module.module

/**
 * Created by Yahia Allam on 15/06/2019
 */
val dataModule = module {

    factory<ICharactersRepository> { CharactersRepository(apiEndpoints = get(), charactersDao = get()) }
}
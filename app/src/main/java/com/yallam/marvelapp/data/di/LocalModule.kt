package com.yallam.marvelapp.data.di

import androidx.room.Room
import com.yallam.marvelapp.data.local.CharactersDao
import com.yallam.marvelapp.data.local.CharactersDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

/**
 * Created by Yahia Allam on 16/06/2019
 */
val localModule = module {

    single<CharactersDatabase> {
        Room.databaseBuilder(androidApplication(), CharactersDatabase::class.java, "characters_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single<CharactersDao> {
        get<CharactersDatabase>().charactersDao()
    }

}
package com.yallam.marvelapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yallam.marvelapp.data.model.CharacterModel

/**
 * Created by Yahia Allam on 16/06/2019
 */
@Database(entities = [CharacterModel::class], version = 1)
abstract class CharactersDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}
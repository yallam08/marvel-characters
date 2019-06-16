package com.yallam.marvelapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yallam.marvelapp.data.model.CharacterModel
import io.reactivex.Single

/**
 * Created by Yahia Allam on 16/06/2019
 */
@Dao
interface CharactersDao {

    @Query("SELECT * FROM character")
    fun getCharacters(): Single<List<CharacterModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterModel)

    @Query("SELECT * FROM character WHERE id = :characterId")
    fun getCharacterById(characterId: Int): Single<CharacterModel>

}
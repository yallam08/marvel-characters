package com.yallam.marvelapp.data

import com.yallam.marvelapp.data.model.CharacterModel
import io.reactivex.Single

interface ICharactersRepository {

    fun getCharacters(): Single<List<CharacterModel>>
    fun getMoreCharacters(): Single<List<CharacterModel>>
    fun getCharacterById(characterId: Long): Single<CharacterModel>
}

package com.yallam.marvelapp.presentation.characterslist

import com.yallam.marvelapp.data.model.CharacterModel

/**
 * Created by Yahia Allam on 16/06/2019
 */
sealed class CharactersListState {
    object Loading : CharactersListState()
    class Ready(val characters: List<CharacterModel>) : CharactersListState()
    class Error(val throwable: Throwable) : CharactersListState()
}
package com.yallam.marvelapp.presentation.characterslist

import com.yallam.marvelapp.data.model.CharacterModel

sealed class CharactersListState {
    object Loading : CharactersListState()
    class Ready(val characters: List<CharacterModel>) : CharactersListState()
    class Error(val throwable: Throwable) : CharactersListState()
}

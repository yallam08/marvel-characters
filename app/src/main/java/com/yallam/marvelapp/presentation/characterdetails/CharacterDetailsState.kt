package com.yallam.marvelapp.presentation.characterdetails

import com.yallam.marvelapp.data.model.CharacterModel

/**
 * Created by Yahia Allam on 18/06/2019
 */
sealed class CharacterDetailsState {
    object Loading : CharacterDetailsState()
    class Ready(val character: CharacterModel) : CharacterDetailsState()
    class Error(val throwable: Throwable) : CharacterDetailsState()
}
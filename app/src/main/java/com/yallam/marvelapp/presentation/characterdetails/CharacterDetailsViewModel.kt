package com.yallam.marvelapp.presentation.characterdetails

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.yallam.marvelapp.base.schedulers.ThreadSchedulers
import com.yallam.marvelapp.data.CharactersRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Yahia Allam on 18/06/2019
 */
class CharacterDetailsViewModel(
    private val charactersRepository: CharactersRepository,
    private val threadSchedulers: ThreadSchedulers,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    private val stateMutableLiveData: MutableLiveData<CharacterDetailsState> = MutableLiveData()

    fun getCharacter(characterId: Long) {
        compositeDisposable.add(
            charactersRepository.getCharacterById(characterId)
                .subscribeOn(threadSchedulers.getIOThread())
                .doOnSubscribe { stateMutableLiveData.postValue(CharacterDetailsState.Loading) }
                .subscribe(
                    { stateMutableLiveData.postValue(CharacterDetailsState.Ready(it)) },
                    { stateMutableLiveData.postValue(CharacterDetailsState.Error(it)) }
                )
        )
    }

    fun observeState(owner: LifecycleOwner, observer: Observer<CharacterDetailsState>) {
        stateMutableLiveData.observe(owner, observer)
    }

    override fun onCleared() {
        if (compositeDisposable.isDisposed.not()) compositeDisposable.dispose()
        super.onCleared()
    }

}
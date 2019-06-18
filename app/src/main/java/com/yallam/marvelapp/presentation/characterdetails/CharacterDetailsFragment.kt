package com.yallam.marvelapp.presentation.characterdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.yallam.marvelapp.R
import com.yallam.marvelapp.base.BaseFragment
import com.yallam.marvelapp.base.extensionfunctions.getDrawableResIdFromName
import com.yallam.marvelapp.data.model.CharacterModel
import kotlinx.android.synthetic.main.fragment_character_details.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.IOException

/**
 * Created by Yahia Allam on 18/06/2019
 */
class CharacterDetailsFragment : BaseFragment() {

    private val viewModel: CharacterDetailsViewModel by viewModel()

    companion object {
        private const val ARGUMENT_CHARACTER_ID = "argument_character_id"

        fun newInstance(character: CharacterModel): CharacterDetailsFragment {
            val bundle = Bundle()
            bundle.putLong(ARGUMENT_CHARACTER_ID, character.id)

            return CharacterDetailsFragment().apply { arguments = bundle }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_character_details, container, false)

        observeViewModel()
        arguments?.getLong(ARGUMENT_CHARACTER_ID)?.let {
            viewModel.getCharacter(it)
        }

        return rootView
    }

    private fun observeViewModel() {
        viewModel.observeState(this, Observer { state ->
            when (state) {
                is CharacterDetailsState.Loading -> showLoading()
                is CharacterDetailsState.Ready -> showCharacterDetails(state.character)
                is CharacterDetailsState.Error -> showError(state.throwable)
            }
        })
    }

    private fun showLoading() {
        tvError.visibility = GONE
        appBarLayout.visibility = GONE
        containerCharacterDetailsContent.visibility = GONE
        progressBarLoading.visibility = VISIBLE
    }

    private fun showCharacterDetails(character: CharacterModel) {
        activity?.getDrawableResIdFromName(character.thumbnail)?.let {
            ivCharacterDetailsCover.setImageResource(it)
        }
        tvCharacterName.text = character.name
        tvCharacterDescription.text = character.description

        progressBarLoading.visibility = GONE
        tvError.visibility = GONE
        appBarLayout.visibility = VISIBLE
        containerCharacterDetailsContent.visibility = VISIBLE
    }

    private fun showError(throwable: Throwable) {
        tvError.text = if (throwable is IOException) {
            getString(R.string.internet_connection_error)
        } else {
            getString(R.string.something_wrong_error)
        }

        progressBarLoading.visibility = GONE
        appBarLayout.visibility = GONE
        appBarLayout.visibility = GONE
        tvError.visibility = VISIBLE
    }

}
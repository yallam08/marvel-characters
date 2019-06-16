package com.yallam.marvelapp.presentation.characterslist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yallam.marvelapp.R
import com.yallam.marvelapp.base.BaseFragment
import com.yallam.marvelapp.data.model.CharacterModel
import kotlinx.android.synthetic.main.fragment_characters_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.IOException

class CharactersListFragment : BaseFragment() {

    private val viewModel: CharactersListViewModel by viewModel()
    private val characterListAdapter: CharacterListAdapter by lazy {
        CharacterListAdapter(emptyList(), ::charactersListItemClickCallback)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_characters_list, container, false)

        observeViewModel()
        viewModel.getCharacters()

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCharacterListRecyclerView()
    }

    private fun initCharacterListRecyclerView() {
        rvCharacters.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = characterListAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.observeState(this, Observer { state ->
            when (state) {
                is CharactersListState.Loading -> showLoading()
                is CharactersListState.Ready -> showCharacters(state.characters)
                is CharactersListState.Error -> showError(state.throwable)
            }
        })
    }

    private fun showLoading() {
        tvError.visibility = GONE
        rvCharacters.visibility = GONE
        progressBarLoading.visibility = VISIBLE
    }

    private fun showCharacters(characters: List<CharacterModel>) {
        characterListAdapter.characters = characters
        characterListAdapter.notifyDataSetChanged()

        progressBarLoading.visibility = GONE
        tvError.visibility = GONE
        rvCharacters.visibility = VISIBLE
    }

    private fun showError(throwable: Throwable) {
        tvError.text = if (throwable is IOException) {
            getString(R.string.internet_connection_error)
        } else {
            getString(R.string.something_wrong_error)
        }

        progressBarLoading.visibility = GONE
        rvCharacters.visibility = GONE
        tvError.visibility = VISIBLE
    }

    private fun charactersListItemClickCallback(character: CharacterModel) {
        //TODO
//        fragmentNavigator.navigateToFragmentSaveState(this, CharacterDetailsFragment.newInstance(character))
    }

}

package com.yallam.marvelapp.presentation.characterslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.yallam.marvelapp.R
import com.yallam.marvelapp.base.extensionfunctions.getDrawableResIdFromName
import com.yallam.marvelapp.base.extensionfunctions.load
import com.yallam.marvelapp.data.model.CharacterModel

/**
 * Created by Yahia Allam on 16/06/2019
 */
class CharacterListAdapter(
    var characters: MutableList<CharacterModel>,
    private val itemClickCallback: (character: CharacterModel) -> Unit
) : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.characters_list_item, parent, false)
        return ViewHolder(parent.context, view)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
    }


    inner class ViewHolder(private val context: Context, view: View) : RecyclerView.ViewHolder(view) {
        private val itemContainer: ConstraintLayout = view.findViewById(R.id.containerCharactersListItem)
        private val itemThumb: ImageView = view.findViewById(R.id.ivCharactersListItemThumb)
        private val itemTitle: TextView = view.findViewById(R.id.tvCharactersListItemTitle)

        fun bind(character: CharacterModel) {
            itemThumb.load(context.getDrawableResIdFromName(character.thumbnail))
            itemTitle.text = character.name
            itemContainer.setOnClickListener { itemClickCallback(character) }
        }
    }
}
package com.yallam.marvelapp.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Yahia Allam on 15/06/2019
 */
class CharacterModel(

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("thumbnail")
    val thumbnail: String
)
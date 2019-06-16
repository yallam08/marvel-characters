package com.yallam.marvelapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Yahia Allam on 15/06/2019
 * TODO: use separate model for db, domain, ui layers
 */
@Entity(tableName = "character")
class CharacterModel(

    @PrimaryKey
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("thumbnail")
    val thumbnail: String
)
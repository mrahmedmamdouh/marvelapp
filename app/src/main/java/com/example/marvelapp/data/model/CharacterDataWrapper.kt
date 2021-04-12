package com.example.marvelapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDataWrapper (
    val code : Int,
    val status : String,
    val data : CharacterDataContainer
)
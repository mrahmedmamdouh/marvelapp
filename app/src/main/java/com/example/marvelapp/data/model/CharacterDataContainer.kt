package com.example.marvelapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDataContainer (
    val offset : Int,
    val limit : Int,
    val total : Int,
    val count : Int,
    val results : List<Character>
)
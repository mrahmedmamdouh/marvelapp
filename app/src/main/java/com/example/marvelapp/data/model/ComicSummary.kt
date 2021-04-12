package com.example.marvelapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicSummary (
    val resourceURI : String,
    val name : String
)
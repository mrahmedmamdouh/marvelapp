package com.example.marvelapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeriesSummary (
    val resourceURI : String,
    val name : String
)
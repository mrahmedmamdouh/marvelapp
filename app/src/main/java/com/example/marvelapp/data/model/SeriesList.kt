package com.example.marvelapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeriesList (
    val available : Int,
    val returned : Int,
    val collectionURI : String,
    val items : List<SeriesSummary>

)
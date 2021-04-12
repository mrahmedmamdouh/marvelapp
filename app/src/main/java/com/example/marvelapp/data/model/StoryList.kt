package com.example.marvelapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoryList (
    val available : Int,
    val returned : Int,
    val collectionURI : String,
    val items : List<StorySummary>
)
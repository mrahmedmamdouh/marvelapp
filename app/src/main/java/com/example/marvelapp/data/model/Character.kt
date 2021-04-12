package com.example.marvelapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character (
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val urls: List<Url>,
    val thumbnail: Image,
    val comics: ComicList,
    val stories: StoryList,
    val events: EventList,
    val series: SeriesList
)
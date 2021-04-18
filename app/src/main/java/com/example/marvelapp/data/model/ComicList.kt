package com.example.marvelapp.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ComicList (
    val available : Int,
    val returned : Int,
    val collectionURI : String,
    val items : List<ComicSummary>
) : Parcelable

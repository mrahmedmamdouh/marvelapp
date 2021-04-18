package com.example.marvelapp.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Url (
    val type : String,
    val url : String
) : Parcelable
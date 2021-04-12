package com.example.marvelapp.data.retrofit

import com.example.marvelapp.BuildConfig
import com.example.marvelapp.data.model.CharacterDataWrapper
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("v1/public/characters?ts=1&apikey=:${BuildConfig.apikey}&hash=:${BuildConfig.hash}&limit=10")
    fun getCharactersAsync(@Query("offset") offset: Int = 0,
                           @Query("nameStartsWith") query : String)
            : Deferred<Response<CharacterDataWrapper>>

}
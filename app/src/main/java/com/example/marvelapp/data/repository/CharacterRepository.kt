package com.example.marvelapp.data.repository

import androidx.paging.Pager
import androidx.paging.liveData
import com.example.marvelapp.data.retrofit.ApiInterface
import com.example.marvelapp.di.ApiModule
import com.example.marvelapp.paging.CharactersDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(
    private val api: ApiInterface
) {
    fun getResults(query: String) =
        Pager(
            config = ApiModule.initPagingConfig(),
            pagingSourceFactory = { CharactersDataSource(api, query) }
        ).liveData
}
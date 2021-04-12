package com.example.marvelapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.marvelapp.data.model.Character
import com.example.marvelapp.data.retrofit.ApiInterface
import com.example.marvelapp.di.ApiModule.initPagingConfig
import com.example.marvelapp.paging.CharactersDataSource

class MainViewModel(private val api: ApiInterface, private val query: String) : ViewModel() {

    var charactersLiveData: LiveData<PagedList<Character>>

    init {
        charactersLiveData = initPagedListBuilder(initPagingConfig()).build()
    }

    private fun initPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, Character> {
        val dataSourceFactory = object : DataSource.Factory<Int, Character>() {
            override fun create(): DataSource<Int, Character> {
                return CharactersDataSource(api, query)
            }
        }
        return LivePagedListBuilder<Int, Character>(dataSourceFactory, config)
    }


}
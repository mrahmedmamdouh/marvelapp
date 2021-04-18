package com.example.marvelapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.marvelapp.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: CharacterRepository) :
    ViewModel() {

    private val query = MutableLiveData<String>()

    init {
        getData("")
    }

    val character = query.switchMap { query -> repo.getResults(query).cachedIn(viewModelScope) }

    fun getData(query: String) {
        this.query.value = query
    }


}
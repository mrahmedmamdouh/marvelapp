package com.example.marvelapp.ui.main

import com.example.marvelapp.data.model.Character

interface CharacterCallBack {

    fun onUserClick(character: Character)
}
package com.example.marvelapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.marvelapp.data.model.Character

class DiffUtilCallBack : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

}

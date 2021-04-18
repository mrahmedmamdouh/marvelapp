package com.example.marvelapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.marvelapp.R
import com.example.marvelapp.data.model.Character
import com.example.marvelapp.databinding.ListItemsBinding
import com.example.marvelapp.utils.DiffUtilCallBack

class CharacterAdapter(private val callBack: CharacterCallBack) : PagingDataAdapter<Character, CharacterAdapter.VH>(DiffUtilCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }


    override fun onBindViewHolder(holder: VH, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(item = currentItem)
    }

    inner class VH(private val binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Character) {
            binding.character = item
            binding.callback = callBack
            Glide.with(itemView).load(item.thumbnail.path + "/landscape_xlarge." + item.thumbnail.extension)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.image)
        }
    }
}
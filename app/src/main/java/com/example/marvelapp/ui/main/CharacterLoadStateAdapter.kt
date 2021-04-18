package com.example.marvelapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.ProgressLoadStateBinding

class CharacterLoadStateAdapter : LoadStateAdapter<CharacterLoadStateAdapter.VH>() {


    class VH(private val binding: ProgressLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.apply {
                progress.isVisible = loadState is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: VH, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): VH {
        val binding =
            ProgressLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }
}
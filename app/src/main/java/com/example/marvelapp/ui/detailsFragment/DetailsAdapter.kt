package com.example.marvelapp.ui.detailsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.marvelapp.R
import com.example.marvelapp.data.model.ComicSummary
import com.example.marvelapp.databinding.SubListItemsBinding

class DetailsAdapter(private val itemType: String) :
    PagingDataAdapter<ComicSummary, DetailsAdapter.VH>(
        DiffUtilCallBack
    ) {
    override fun onBindViewHolder(holder: DetailsAdapter.VH, position: Int) {
        val item = getItem(position)
        if (item != null) holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsAdapter.VH {
        val binding =
            SubListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    inner class VH(private val binding: SubListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ComicSummary) {
            binding.apply {
                when (itemType) {
                    "Comics" -> name.text = item.name
                    "Events" -> name.text = item.name
                    "Series" -> name.text = item.name
                    "Stories" -> name.text = item.name
                }
//                Glide.with(itemView)
//                    .load(item.thumbnail.path + "/landscape_xlarge." + character.thumbnail.extension)
//                    .placeholder(R.drawable.image_placeholder)
//                    .error(R.drawable.image_placeholder)
//                    .transition(DrawableTransitionOptions.withCrossFade())
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(image)
            }

        }
    }

    companion object {
        private val DiffUtilCallBack = object : DiffUtil.ItemCallback<ComicSummary>() {
            override fun areItemsTheSame(oldItem: ComicSummary, newItem: ComicSummary): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: ComicSummary, newItem: ComicSummary): Boolean {
                return oldItem == newItem
            }
        }
    }

}
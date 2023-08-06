package com.example.testapplication.presentation.tabs.adapters.favoriteadapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.MovieItem


object FavDiffCallBack: DiffUtil.ItemCallback<MovieItem>() {

    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem == newItem
    }
}
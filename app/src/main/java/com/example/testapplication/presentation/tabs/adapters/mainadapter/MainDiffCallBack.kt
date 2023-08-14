package com.example.testapplication.presentation.tabs.adapters.mainadapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.MovieItem

object MainDiffCallBack: DiffUtil.ItemCallback<MovieItem>() {


    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem == newItem
    }
}
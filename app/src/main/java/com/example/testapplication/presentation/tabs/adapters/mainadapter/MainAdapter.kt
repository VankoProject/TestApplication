package com.example.testapplication.presentation.tabs.adapters.mainadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.testapplication.R
import com.example.testapplication.databinding.MovieItemBinding
import com.example.testapplication.domain.MovieItem

interface Listener {

    fun onMovieClick(movieItem: MovieItem)

    fun onIconClick(movieItem: MovieItem)
}

class MainAdapter(
    private val listener: Listener
) : ListAdapter<MovieItem, MainViewHolder>(MainDiffCallBack), View.OnClickListener {

    override fun onClick(p0: View?) {
        val movieInfo = p0?.tag as MovieItem
        when (p0.id) {
            R.id.ivPoster -> listener.onMovieClick(movieInfo)
            R.id.ivAddToDb -> listener.onIconClick(movieInfo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.ivPoster.setOnClickListener(this)
        binding.ivAddToDb.setOnClickListener(this)

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.ivPoster.tag = item
        holder.binding.ivAddToDb.tag = item

        with(holder.binding) {
            tvTitle.text = item.title
            tvReleaseDate.text = item.releaseDate
            ratingBar.rating = item.voteAverage.toFloat()

            Glide.with(holder.binding.root.context)
                .load(URL_POSTER + item.posterPath)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(ivPoster)
        }
    }

    companion object {
        private const val URL_POSTER = "https://image.tmdb.org/t/p/w500"
    }
}
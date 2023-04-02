package com.example.testapplication.presentation.tabs.adapters.favoriteadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.domain.domain.MovieItem
import com.example.testapplication.R
import com.example.testapplication.databinding.MovieItemDetailBinding
import com.example.testapplication.presentation.setUpRating


class FavAdapter(
    private val listener: FavListener
) : ListAdapter<MovieItem, FavViewHolder>(FavDiffCallBack), View.OnClickListener {

    override fun onClick(p0: View?) {
        val movieItem = p0?.tag as MovieItem
        when (p0.id) {
            R.id.icon_delete -> listener.onClickButtonDelete(movieItem)
            R.id.article_image -> listener.onCurrentMoviePosition(movieItem)
            R.id.tvName -> listener.onCurrentMoviePosition(movieItem)
            R.id.tvScore -> listener.onCurrentMoviePosition(movieItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val binding = MovieItemDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        binding.iconDelete.setOnClickListener(this)
        binding.tvName.setOnClickListener(this)
        binding.tvScore.setOnClickListener(this)
        binding.articleImage.setOnClickListener(this)


        return FavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val movieItem = getItem(position)

        with(holder.binding){
            articleImage.tag = movieItem
            tvName.tag = movieItem
            tvScore.tag = movieItem
            iconDelete.tag = movieItem

            tvName.text = movieItem.title
            tvScore.text = movieItem.voteAverage.toString()
            ratingBar.rating = setUpRating(movieItem.voteAverage.toFloat())
            Glide.with(holder.binding.root.context)
                .load(URL_POSTER + movieItem.posterPath)
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(articleImage)
        }
    }

    companion object {
        private const val URL_POSTER = "https://image.tmdb.org/t/p/w500"
    }

    interface FavListener {

        fun onClickButtonDelete(movieItem: MovieItem)

        fun onCurrentMoviePosition(movieItem: MovieItem)

    }
}
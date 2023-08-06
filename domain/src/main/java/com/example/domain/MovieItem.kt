package com.example.domain

data class MovieItem(
    val id: Int,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val isFavorite: Boolean,
)

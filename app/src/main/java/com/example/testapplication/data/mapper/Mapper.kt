package com.example.testapplication.data.mapper

import com.example.testapplication.data.database.MovieDbModel
import com.example.testapplication.data.network.model.MovieItemDto
import com.example.testapplication.domain.MovieItem

fun movieItemToDbModel(movieItem: MovieItem) = MovieDbModel(
    id = movieItem.id,
    overview = movieItem.overview,
    posterPath = movieItem.posterPath,
    releaseDate = movieItem.releaseDate,
    title = movieItem.title,
    voteAverage = movieItem.voteAverage,
    isFavorite = true
)

fun changeMovieStatus(movieItem: MovieItem) = MovieDbModel(
    id = movieItem.id,
    overview = movieItem.overview,
    posterPath = movieItem.posterPath,
    releaseDate = movieItem.releaseDate,
    title = movieItem.title,
    voteAverage = movieItem.voteAverage,
    isFavorite = false
)


val MovieItemDto.modelDtoToMovieDbModel
    get() = MovieDbModel(
        id = id,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        isFavorite = false
    )

fun dbModelToMovieItem(db: MovieDbModel) = MovieItem(
    id = db.id,
    overview = db.overview,
    posterPath = db.posterPath,
    releaseDate = db.releaseDate,
    title = db.title,
    voteAverage = db.voteAverage,
    isFavorite = db.isFavorite
)


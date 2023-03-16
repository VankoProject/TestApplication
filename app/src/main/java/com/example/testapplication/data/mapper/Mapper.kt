package com.example.testapplication.data.mapper

import com.example.testapplication.data.database.MovieDbModel
import com.example.testapplication.data.network.model.MovieItemDto
import com.example.testapplication.data.network.model.MovieListDto
import com.example.testapplication.domain.MovieItem

class Mapper {

    fun movieItemToDbModel(movieItem: MovieItem) = MovieDbModel(
        id = movieItem.id,
        overview = movieItem.overview,
        posterPath = movieItem.posterPath,
        releaseDate = movieItem.releaseDate,
        title = movieItem.title,
        voteAverage = movieItem.voteAverage
    )

    fun modelDtoToMovieItem(dto: MovieItemDto) = MovieItem(
        id = dto.id,
        overview = dto.overview,
        posterPath = dto.posterPath,
        releaseDate = dto.releaseDate,
        title = dto.title,
        voteAverage = dto.voteAverage
    )

    fun dbModelToMovieItem(db: MovieDbModel) = MovieItem(
        id = db.id,
        overview = db.overview,
        posterPath = db.posterPath,
        releaseDate = db.releaseDate,
        title = db.title,
        voteAverage = db.voteAverage
    )

    fun listDtoToListMovie(listDto: MovieListDto) {
        val list = listDto.results.map {
            modelDtoToMovieItem(it)
        }
    }
}
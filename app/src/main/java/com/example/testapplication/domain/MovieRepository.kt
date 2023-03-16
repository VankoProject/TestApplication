package com.example.testapplication.domain

import androidx.lifecycle.LiveData

interface MovieRepository {

    fun getFavoriteMovies(): LiveData<List<MovieItem>>

    suspend fun getDetailMovieInfo(movieId: Int): MovieItem

    suspend fun getPopularMovies(): List<MovieItem>

    suspend fun saveMovie(movieItem: MovieItem)

    suspend fun deleteMovie(movieItem: MovieItem)

}
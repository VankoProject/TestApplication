package com.example.domain.domain

import androidx.lifecycle.LiveData


interface MovieRepository {

    fun getFavoriteMovies(): LiveData<List<MovieItem>>

    suspend fun getDetailMovieInfo(movieId: Int): MovieItem

    fun getPopularMovies(): LiveData<List<MovieItem>>

    suspend fun saveMovie(movieItem: MovieItem)

    suspend fun deleteMovie(movieItem: MovieItem)

    suspend fun loadDataToDb()

    suspend fun searchMovie(title: String): MovieItem

}
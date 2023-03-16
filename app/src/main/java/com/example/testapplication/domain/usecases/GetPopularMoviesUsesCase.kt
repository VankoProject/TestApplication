package com.example.testapplication.domain.usecases

import com.example.testapplication.domain.MovieItem
import com.example.testapplication.domain.MovieRepository

class GetPopularMoviesUsesCase(private val repository: MovieRepository) {

    suspend fun execute(): List<MovieItem> {
        return repository.getPopularMovies()
    }
}
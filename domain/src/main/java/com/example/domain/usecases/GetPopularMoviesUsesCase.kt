package com.example.domain.usecases

import androidx.lifecycle.LiveData
import com.example.domain.MovieItem
import com.example.domain.MovieRepository

class GetPopularMoviesUsesCase(private val repository: MovieRepository) {

    fun execute(): LiveData<List<MovieItem>> {
        return repository.getPopularMovies()
    }
}
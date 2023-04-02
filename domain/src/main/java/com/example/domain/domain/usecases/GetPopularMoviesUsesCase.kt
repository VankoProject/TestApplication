package com.example.domain.domain.usecases

import androidx.lifecycle.LiveData
import com.example.domain.domain.MovieItem
import com.example.domain.domain.MovieRepository

class GetPopularMoviesUsesCase(private val repository: MovieRepository) {

    fun execute(): LiveData<List<MovieItem>> {
        return repository.getPopularMovies()
    }
}
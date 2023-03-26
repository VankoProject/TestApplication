package com.example.testapplication.domain.usecases

import androidx.lifecycle.LiveData
import com.example.testapplication.domain.MovieItem
import com.example.testapplication.domain.MovieRepository

class GetPopularMoviesUsesCase(private val repository: MovieRepository) {

    fun execute(): LiveData<List<MovieItem>> {
        return repository.getPopularMovies()
    }
}
package com.example.testapplication.domain.usecases

import com.example.testapplication.domain.MovieItem
import com.example.testapplication.domain.MovieRepository

class SearchMovieUseCase(private val repository: MovieRepository) {

    suspend fun execute(title: String): MovieItem {
        return repository.searchMovie(title)
    }
}
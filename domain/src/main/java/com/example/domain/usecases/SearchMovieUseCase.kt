package com.example.domain.usecases

import com.example.domain.MovieItem
import com.example.domain.MovieRepository

class SearchMovieUseCase(private val repository: MovieRepository) {

    suspend fun execute(title: String): MovieItem {
        return repository.searchMovie(title)
    }
}
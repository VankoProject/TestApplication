package com.example.domain.domain.usecases

import com.example.domain.domain.MovieItem
import com.example.domain.domain.MovieRepository

class SearchMovieUseCase(private val repository: MovieRepository) {

    suspend fun execute(title: String): MovieItem {
        return repository.searchMovie(title)
    }
}
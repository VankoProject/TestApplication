package com.example.domain.domain.usecases

import com.example.domain.domain.MovieItem
import com.example.domain.domain.MovieRepository

class DeleteMovieItemUseCase(private val repository: MovieRepository) {

    suspend fun execute(movieItem: MovieItem) {
        repository.deleteMovie(movieItem)
    }
}
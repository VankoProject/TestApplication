package com.example.domain.usecases

import com.example.domain.MovieItem
import com.example.domain.MovieRepository

class SaveMovieItemUseCase(private val repository: MovieRepository) {

    suspend fun execute(movieItem: MovieItem) {
        repository.saveMovie(movieItem)
    }
}
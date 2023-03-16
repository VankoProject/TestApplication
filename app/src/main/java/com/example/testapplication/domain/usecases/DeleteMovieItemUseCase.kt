package com.example.testapplication.domain.usecases

import com.example.testapplication.domain.MovieItem
import com.example.testapplication.domain.MovieRepository

class DeleteMovieItemUseCase (private val repository: MovieRepository) {

    suspend fun execute (movieItem: MovieItem) {
        repository.deleteMovie(movieItem)
    }
}
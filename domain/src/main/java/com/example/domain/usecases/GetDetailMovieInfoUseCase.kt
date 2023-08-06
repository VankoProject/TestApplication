package com.example.domain.usecases

import com.example.domain.MovieItem
import com.example.domain.MovieRepository

class GetDetailMovieInfoUseCase(private val repository: MovieRepository) {

    suspend fun execute(movieId: Int): MovieItem {
        return repository.getDetailMovieInfo(movieId)
    }
}
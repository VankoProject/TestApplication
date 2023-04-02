package com.example.domain.domain.usecases

import com.example.domain.domain.MovieItem
import com.example.domain.domain.MovieRepository

class GetDetailMovieInfoUseCase(private val repository: MovieRepository) {

    suspend fun execute(movieId: Int): MovieItem {
        return repository.getDetailMovieInfo(movieId)
    }
}
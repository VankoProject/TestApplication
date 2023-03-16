package com.example.testapplication.domain.usecases

import androidx.lifecycle.LiveData
import com.example.testapplication.domain.MovieItem
import com.example.testapplication.domain.MovieRepository

class GetDetailMovieInfoUseCase (private val repository: MovieRepository) {

    suspend fun execute(movieId: Int): MovieItem {
        return repository.getDetailMovieInfo(movieId)
    }
}
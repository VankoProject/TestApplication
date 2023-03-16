package com.example.testapplication.domain.usecases

import androidx.lifecycle.LiveData
import com.example.testapplication.domain.MovieItem
import com.example.testapplication.domain.MovieRepository

class GetFavoriteMoviesUseCase (private val repository: MovieRepository) {

    fun execute(): LiveData<List<MovieItem>> {
        return repository.getFavoriteMovies()
    }
}
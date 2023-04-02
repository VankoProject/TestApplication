package com.example.domain.domain.usecases

import androidx.lifecycle.LiveData
import com.example.domain.domain.MovieItem
import com.example.domain.domain.MovieRepository

class GetFavoriteMoviesUseCase(private val repository: MovieRepository) {

    fun execute(): LiveData<List<MovieItem>> {
        return repository.getFavoriteMovies()
    }
}
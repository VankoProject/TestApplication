package com.example.testapplication.presentation.tabs.favorite

import androidx.lifecycle.ViewModel
import com.example.testapplication.domain.MovieItem
import com.example.testapplication.domain.usecases.DeleteMovieItemUseCase
import com.example.testapplication.domain.usecases.GetFavoriteMoviesUseCase

class FavoriteViewModel(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val deleteMovieItemUseCase: DeleteMovieItemUseCase
): ViewModel() {

    val movieFavoriteListLD = getFavoriteMoviesUseCase.execute()

    suspend fun deleteFavoriteMovie(movieItem: MovieItem) {
        deleteMovieItemUseCase.execute(movieItem)
    }
}
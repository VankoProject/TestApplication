package com.example.testapplication.presentation.tabs.favorite

import androidx.lifecycle.ViewModel
import com.example.domain.MovieItem
import com.example.domain.usecases.DeleteMovieItemUseCase
import com.example.domain.usecases.GetFavoriteMoviesUseCase

class FavoriteViewModel(
    getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val deleteMovieItemUseCase: DeleteMovieItemUseCase
): ViewModel() {

    val movieFavoriteListLD = getFavoriteMoviesUseCase.execute()

    suspend fun deleteFavoriteMovie(movieItem: MovieItem) {
        deleteMovieItemUseCase.execute(movieItem)
    }
}